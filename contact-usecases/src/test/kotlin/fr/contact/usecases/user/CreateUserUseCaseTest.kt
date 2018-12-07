package fr.contact.usecases.user

import fr.contact.core.entities.PhoneNumber
import fr.contact.core.entities.User
import fr.contact.usecases.gateways.UserRepository
import org.amshove.kluent.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreateUserUseCaseTest {

    private val validPhoneNumber = PhoneNumber("0812233211")
    private val user = User("Charles", validPhoneNumber)

    private lateinit var userRepository: UserRepository
    private lateinit var sut: CreateUserUseCase

    @BeforeEach
    internal fun setUp() {
        userRepository = mock(UserRepository::class)
        sut = CreateUserUseCase(userRepository)
    }

    @Test
    internal fun `should create a user given an available phone number`() {

        When calling userRepository.isUserPhoneNumberAvailable(validPhoneNumber) itReturns  true

        sut.execute(user)

        Verify on userRepository that userRepository.isUserPhoneNumberAvailable(validPhoneNumber) was called
        Verify on userRepository that userRepository.save(user) was called
    }


    @Test
    internal fun `should throw phone number already in use exception given a phone number used by an other user`() {

        When calling userRepository.isUserPhoneNumberAvailable(validPhoneNumber) itReturns  false

        val creationOfUserWithUnavailablePhoneNumber = { sut.execute(user) }

        val number = user.mobilePhoneNumber.number
        creationOfUserWithUnavailablePhoneNumber shouldThrow PhoneNumberAlreadyInUseException::class withMessage "$number is already used by another user"

        Verify on userRepository that userRepository.isUserPhoneNumberAvailable(validPhoneNumber) was called
        VerifyNoFurtherInteractions on userRepository
    }
}