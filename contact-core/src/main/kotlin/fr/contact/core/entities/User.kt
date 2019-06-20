package fr.contact.core.entities

import fr.contact.core.entities.phonenumber.PhoneNumber

data class User(override val name: String,
                val mobilePhoneNumber: PhoneNumber,
                val contacts: MutableList<Contact>) :Person(name) {

    fun hasContact(contact: Contact): Boolean {
        return contacts.any { c -> c.mobilePhoneNumber == contact.mobilePhoneNumber }
    }
}

