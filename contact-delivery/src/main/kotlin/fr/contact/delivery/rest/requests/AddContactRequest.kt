package fr.contact.delivery.rest.requests

import com.beust.klaxon.Klaxon
import fr.contact.core.entities.Contact
import fr.contact.core.entities.phonenumber.PhoneNumber
import spark.Request

class AddContactRequest(val name:String ,val phoneNumber:String, val nickName:String) {
    companion object
}

fun AddContactRequest.toContact(): Contact {
    return Contact(name, PhoneNumber(phoneNumber), nickName)
}

fun AddContactRequest.Companion.fromRequest(request: Request) : AddContactRequest? {
    val body :String = request.body()!!
    return  Klaxon().parse<AddContactRequest>(body)
}
