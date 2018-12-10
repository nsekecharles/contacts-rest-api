package fr.contact.delivery.rest

import com.beust.klaxon.Klaxon
import fr.contact.delivery.rest.requests.AddContactRequest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddContactToUserEndPointTest: ServerTest() {

    private val ADD_CONTACT_END_POINT = "users/0000000000"
    private var EXISTING_CONTACT = AddContactRequest("initialContact", "0600000000", "")
    private var NEW_CONTACT = AddContactRequest("new contact", "0600000001", "")

    @Test
    internal fun `adding a duplicated contact to a user should return a 409 response code`() {

        val contactExistsErrorMessage = "there is already a contact with this phone number"
        val request = RestAssured.with()
                .contentType(ContentType.JSON)
                .body(Klaxon().toJsonString(EXISTING_CONTACT))


        request.`when`()
                .post(ADD_CONTACT_END_POINT)
                .then()
                .statusCode(HttpStatus.SC_CONFLICT)
                .body(equalTo(contactExistsErrorMessage))
    }

    @Test
    internal fun `adding a new contact should return a 201 response`() {

        val request = RestAssured.with()
                .contentType(ContentType.JSON)
                .body(Klaxon().toJsonString(NEW_CONTACT))

        request.`when`()
                .post(ADD_CONTACT_END_POINT)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo(NEW_CONTACT.name))
                .body("mobilePhoneNumber.number", equalTo(NEW_CONTACT.phoneNumber))
                .body("nickName", equalTo(NEW_CONTACT.nickName))

    }
}