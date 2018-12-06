package fr.contact.usecases.user

import fr.contact.core.entities.PhoneNumber
import fr.contact.core.entities.User

class CreateUserUseCase(private val userRepository: UserRepository) {

    fun execute(user: User) : User {
        when {
            userRepository.isUserPhoneNumberAvailable(user.mobilePhoneNumber) -> return userRepository.save(user)
            else -> throw PhoneNumberAllreadyInUseException("$user.mobilePhoneNumber.number is allready used by another user")
        }
    }

    interface UserRepository {
        fun isUserPhoneNumberAvailable(phoneNumber: PhoneNumber) : Boolean
        fun save(user: User) : User
    }
}