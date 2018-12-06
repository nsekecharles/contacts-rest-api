package fr.contact.core.entities

import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class PhoneNumberTest : Spek({

    given("0630560411 phone number") {
        on("creating phone number") {
            val validPhoneNumber = PhoneNumber("0630560411")
            it("creates 0630560411 phone number") {
                validPhoneNumber.number shouldEqual "0630560411"
            }
        }
    }

    given("invalid phone numbers") {
        val invalidPhoneNumbers = listOf("04040404", "000Z000000", "ffffffffff", "aaaa")
        invalidPhoneNumbers.forEach { value ->
            on("creating $value phone number") {
                val invalidPhoneNumberCreation = { PhoneNumber(value) }
                val expectedErrorMessage = "$value is not a valid phone number"
                it("throws $expectedErrorMessage") {
                    invalidPhoneNumberCreation shouldThrow InvalidPhoneNumberException::class withMessage expectedErrorMessage
                }
            }
        }
    }
})