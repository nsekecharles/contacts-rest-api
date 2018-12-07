package fr.contact.delivery.rest

import com.beust.klaxon.Klaxon
import fr.contact.core.entities.User
import fr.contact.delivery.rest.requests.CreateUserRequest
import fr.contact.delivery.rest.requests.fromRequest
import fr.contact.delivery.rest.requests.toUser
import fr.contact.usecases.gateways.UserRepository
import fr.contact.usecases.user.CreateUserUseCase
import spark.Spark
import spark.kotlin.port
import spark.kotlin.post

class Server(val userRepository: UserRepository) {

    fun start() {
        setupConfigurations()
        createUsersEndPoint()
    }

    fun stop() = Spark::stop


    private fun createUsersEndPoint() {
        val createUserUseCase = CreateUserUseCase(userRepository)
        post("/users") {
            val user = CreateUserRequest.fromRequest(request)!!.toUser()
            createUserUseCase.execute(user)
            status(201)
            type("application/json")
            user.toJson()
        }
    }

    private fun setupConfigurations() {
        port(4490)
    }

    fun User.toJson() : String {
        return Klaxon().toJsonString(this)
    }
}