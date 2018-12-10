package fr.contact.delivery.rest

import fr.contact.core.entities.Contact
import fr.contact.core.entities.User
import fr.contact.core.entities.phonenumber.PhoneNumber
import fr.contact.usecases.gateways.UserRepository

//TODO move this to a data module
class InMemoryUsers : UserRepository {

    private var users: MutableList<User> = mutableListOf(User("initialUserForTest", PhoneNumber("0000000000")))
    private var contacts: MutableMap<User, MutableList<Contact>> = mutableMapOf(users.get(0) to mutableListOf(Contact("initialContact", PhoneNumber("0600000000"))))

    override fun isUserPhoneNumberAvailable(phoneNumber: PhoneNumber): Boolean {
        return !users.any { it-> it.mobilePhoneNumber == phoneNumber  }
    }

    override fun save(user: User) {
        users.add(user)
    }

    override fun addContact(user: User, contact: Contact) {
        contacts.get(user)?.add(contact) ?: contacts.put(user, mutableListOf(contact))
    }

    override fun isContactInUserContacts(contact: Contact, user: User): Boolean {
        return contacts.get(user)?.any { c -> c.mobilePhoneNumber == contact.mobilePhoneNumber } ?: false
    }

    override fun getUser(phoneNumber: String): User {
        return users.first { user -> user.mobilePhoneNumber == PhoneNumber(phoneNumber) }
    }

}