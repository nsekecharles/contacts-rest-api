package fr.contact.core.beans

import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class PhoneNumberTest : Spek({

    given("A valid phone number creation") {

        on("creating phone number 0630560411") {

            val validPhoneNumber = PhoneNumber("0630560411")

            it("creates 0630560411 phone number") {
                validPhoneNumber.number shouldEqual "0630560411"
            }
        }
    }

    given("creating invalid phone number") {

        val invalidPhoneNumber = listOf("04040404", "000Z000000", "ffffffffff", "aaaa")

        invalidPhoneNumber.forEach { value ->

            on("$value") {

                val invalidPhoneNumberCreation = { PhoneNumber(value) }
                val expectedErrorMessage = "$value is not a valid phone number"

                it("throws $expectedErrorMessage") {
                    invalidPhoneNumberCreation shouldThrow InvalidPhoneNumberException::class withMessage expectedErrorMessage
                }
            }
        }
    }
})