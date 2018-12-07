package fr.contact.core.entities

import fr.contact.core.entities.phonenumber.InvalidPhoneNumberException
import fr.contact.core.entities.phonenumber.PhoneNumber
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PhoneNumberTest {

    @Test
    internal fun `should create a phone number given a valid phone number`() {

        val actual = PhoneNumber("0630560411")

        actual.number shouldEqual "0630560411"
    }

    @ParameterizedTest(name = "{index} => try to create a phone number = {0}")
    @ValueSource(strings = ["04040404", "000Z000000", "ffffffffff", "aaaa"])
    internal fun `should throw InvalidPhoneNumberException given an invalid phone number`(invalidPhoneNumber: String) {

        val invalidPhoneNumberCreation = { PhoneNumber(invalidPhoneNumber) }

        val expectedErrorMessage = "$invalidPhoneNumber is not a valid phone number"

        invalidPhoneNumberCreation shouldThrow InvalidPhoneNumberException::class withMessage expectedErrorMessage
    }
}