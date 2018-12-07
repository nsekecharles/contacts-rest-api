package fr.contact.core.entities.phonenumber

data class PhoneNumber(val number: String) {

    private val phoneNumberRegex = "^[0-9]{10}$".toRegex()

    init {
        if(!(number matches  phoneNumberRegex)) throw InvalidPhoneNumberException("$number is not a valid phone number")
    }
}