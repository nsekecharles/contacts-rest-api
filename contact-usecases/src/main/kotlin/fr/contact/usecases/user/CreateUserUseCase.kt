package fr.contact.usecases.user

import fr.contact.core.entities.PhoneNumber
import fr.contact.core.entities.User

class CreateUserUseCase(private val userRepository: UserRepository) {

    fun execute(user: User) {
        val phoneNumber = user.mobilePhoneNumber.number
        when {
            userRepository.isUserPhoneNumberAvailable(user.mobilePhoneNumber) -> userRepository.save(user)
            else -> throw PhoneNumberAlreadyInUseException("$phoneNumber is already used by another user")
        }
    }

    interface UserRepository {
        fun isUserPhoneNumberAvailable(phoneNumber: PhoneNumber) : Boolean
        fun save(user: User)
    }
}