package fr.contact.delivery.rest.requests

import com.beust.klaxon.Klaxon
import fr.contact.core.entities.phonenumber.PhoneNumber
import fr.contact.core.entities.User
import spark.Request

data class CreateUserRequest(val name: String, val phoneNumber: String) {
    companion object
}

fun CreateUserRequest.toUser(): User {
    return User(name, PhoneNumber(phoneNumber))
}

fun CreateUserRequest.Companion.fromRequest(request: Request) : CreateUserRequest? {
    val body :String = request.body()!!
    return  Klaxon().parse<CreateUserRequest>(body)
}


