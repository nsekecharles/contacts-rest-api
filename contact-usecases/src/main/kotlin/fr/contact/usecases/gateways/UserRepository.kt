package fr.contact.usecases.gateways

import fr.contact.core.entities.User
import fr.contact.usecases.user.AddContactUseCase
import fr.contact.usecases.user.CreateUserUseCase

interface UserRepository:CreateUserUseCase.UserRepository, AddContactUseCase.UserContactRepository {
    fun getUser(phoneNumber: String): User
}