package fr.contact.usecases.user

import fr.contact.core.entities.PhoneNumber
import fr.contact.core.entities.User
import fr.contact.usecases.gateways.UserRepository
import org.amshove.kluent.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class CreateUserUseCaseTest : Spek({

    val userRepository = mock(UserRepository::class)
    val sut  = CreateUserUseCase(userRepository)

    given("A user") {
        val mobilePhoneNumber = PhoneNumber("0812233211")

        on("creating the user") {
            val user = User("Charles", mobilePhoneNumber)
            When calling userRepository.isUserPhoneNumberAvailable(mobilePhoneNumber) itReturns  true
            When calling userRepository.save(user) itReturns user
            sut.execute(user)
            it("save the user") {
                Verify on userRepository that userRepository.save(user) was called
            }
        }
    }
})