package fr.contact.core.beans

data class PhoneNumber(val number: String) {

    val phoneNumberRegex = "^[0-9]{10}$".toRegex()

    init {
        if(!(number matches  phoneNumberRegex)) throw InvalidPhoneNumberException("$number is not a valid phone number")

    }
}