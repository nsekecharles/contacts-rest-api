package fr.contact.delivery.rest

import fr.contact.core.entities.PhoneNumber
import fr.contact.core.entities.User
import fr.contact.usecases.gateways.UserRepository

//TODO move this to a data module
class InMemoryUsers : UserRepository {

    private var users: MutableList<User> = mutableListOf()

    override fun isUserPhoneNumberAvailable(phoneNumber: PhoneNumber): Boolean {
        return !users.any { it-> it.mobilePhoneNumber == phoneNumber  }
    }

    override fun save(user: User) {
        users.add(user)
    }

}