package fr.contact.core.entities

import fr.contact.core.entities.phonenumber.PhoneNumber

data class User(override val name: String, val mobilePhoneNumber: PhoneNumber) :Person(name)