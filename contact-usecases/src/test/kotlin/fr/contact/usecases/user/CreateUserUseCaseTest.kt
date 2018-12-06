package fr.contact.usecases.user

import fr.contact.core.entities.PhoneNumber
import fr.contact.core.entities.User
import fr.contact.usecases.gateways.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class CreateUserUseCaseTest : Spek({

    val userRepository = mockk<UserRepository>()
    val sut  = CreateUserUseCase(userRepository)

    given("A user") {
        val mobilePhoneNumber = PhoneNumber("0812233211")

        every { userRepository.isUserPhoneNumberAvailable(mobilePhoneNumber) } returns true

        on("creating the user") {
            val user = User("Charles", mobilePhoneNumber)
            every { userRepository.save(user) } returns user
            sut.execute(user)
            it("save the user") {
                verify { userRepository.save(user) }
            }
        }
    }
})