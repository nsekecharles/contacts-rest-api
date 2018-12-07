package fr.contact.core.entities

import fr.contact.core.entities.phonenumber.PhoneNumber

data class Contact(override val name: String, val mobilePhoneNumber: PhoneNumber, var nickName: String = ""): Person(name)