package fr.contact.usecases.user

import fr.contact.core.entities.Contact
import fr.contact.core.entities.User

class AddContactUseCase(private val userContactRepository: UserContactRepository) {

    fun execute(user: User, contactToAdd: Contact) {

        if(user.hasContact(contactToAdd)) {
            val contactPhoneNumber = contactToAdd.mobilePhoneNumber.number
            throw ContactAlreadyExistsException("$contactPhoneNumber, is already in user's contact list")
        }

        userContactRepository.addContact(user, contactToAdd)

    }

    interface UserContactRepository {
        fun addContact(user: User, contact: Contact)
    }
}