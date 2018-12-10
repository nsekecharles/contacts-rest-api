package fr.contact.delivery.rest

import com.beust.klaxon.Klaxon
import fr.contact.core.entities.Contact
import fr.contact.core.entities.User
import fr.contact.delivery.rest.requests.*
import fr.contact.usecases.gateways.UserRepository
import fr.contact.usecases.user.AddContactUseCase
import fr.contact.usecases.user.ContactAlreadyExistsException
import fr.contact.usecases.user.CreateUserUseCase
import fr.contact.usecases.user.PhoneNumberAlreadyInUseException
import spark.Spark
import spark.kotlin.port
import spark.kotlin.post

class Server(val userRepository: UserRepository) {

    fun start() {
        setupConfigurations()
        createUsersEndPoint()
        addContactUseCase()
    }

    private fun addContactUseCase() {
        var addContactUseCase = AddContactUseCase(userRepository)
        post("users/:userPhoneNumber") {
           val userPhoneNumber = request.params(":userPhoneNumber")
            val contactToAdd = AddContactRequest.fromRequest(request)!!.toContact()
            try {
                addContactUseCase.execute(userRepository.getUser(userPhoneNumber), contactToAdd)
                type("application/json")
                status(201)
                contactToAdd.toJson()
            } catch (e: ContactAlreadyExistsException) {
                status(409)
                type("application/json")
                "there is already a contact with this phone number"
            }
        }
    }

    fun stop() = Spark::stop


    private fun createUsersEndPoint() {
        val createUserUseCase = CreateUserUseCase(userRepository)
        post("/users") {
            try {
                val user = CreateUserRequest.fromRequest(request)!!.toUser()
                createUserUseCase.execute(user)
                status(201)
                type("application/json")
                user.toJson()
            } catch (e: PhoneNumberAlreadyInUseException) {
                status(409)
                type("application/json")
                "The phone number is already in use."
            }

        }
    }

    private fun setupConfigurations() {
        port(4490)
    }

    fun User.toJson() : String {
        return Klaxon().toJsonString(this)
    }

    fun Contact.toJson() : String {
        return Klaxon().toJsonString(this)
    }
}