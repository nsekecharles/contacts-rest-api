package fr.contact.usecases.user

import fr.contact.core.entities.Contact
import fr.contact.core.entities.User
import fr.contact.core.entities.phonenumber.PhoneNumber
import fr.contact.usecases.user.AddContactUseCase.UserContactRepository
import org.amshove.kluent.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class AddContactUseCaseTest {

    private val user = User("Charles", PhoneNumber("0223322111"))
    private val contactToAdd = Contact("Andre", PhoneNumber("0321222022"))

    private lateinit var sut: AddContactUseCase
    private lateinit var userContactRepository: UserContactRepository

    @BeforeEach
    internal fun setUp() {
        userContactRepository = mock(UserContactRepository::class)
        sut = AddContactUseCase(userContactRepository)
    }

    @Test
    internal fun `should add a contact to user contacts`() {

        sut.execute(user, contactToAdd)

        Verify on userContactRepository that userContactRepository.addContact(user, contactToAdd) was called
    }

    @Test
    internal fun `should  throw contact already exist giving a contact phone number in contacts`() {

        val contactPhoneNumber = contactToAdd.mobilePhoneNumber.number
        When calling userContactRepository.isContactInUserContacts(contactToAdd, user) itReturns true

        var addindContactExecution = {sut.execute(user, contactToAdd)}
        addindContactExecution shouldThrow ContactAlreadyExists::class withMessage "$contactPhoneNumber, is already in user's contact list"

        Verify on userContactRepository that userContactRepository.isContactInUserContacts(contactToAdd, user) was called
        VerifyNoFurtherInteractions on userContactRepository
    }
}