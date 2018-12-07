package fr.contact.usecases.gateways

import fr.contact.usecases.user.AddContactUseCase
import fr.contact.usecases.user.CreateUserUseCase

interface UserRepository:CreateUserUseCase.UserRepository, AddContactUseCase.UserContactRepository