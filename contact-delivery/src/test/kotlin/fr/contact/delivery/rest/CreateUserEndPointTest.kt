package fr.contact.delivery.rest

import com.beust.klaxon.Klaxon
import fr.contact.delivery.rest.requests.CreateUserRequest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateUserEndPointTest: ServerTest() {

    private val CREATE_USER_END_POINT = "/users"
    private val CREATE_NEW_USER_REQUEST = CreateUserRequest("test", "0192292929")
    private val CREATE_EXISTING_USER_REQUEST = CreateUserRequest("another user", "0000000000")

    @Test
    internal fun `creating a user should return a 201 response code`() {

        val request = RestAssured.with()
                .contentType(ContentType.JSON)
                .body(Klaxon().toJsonString(CREATE_NEW_USER_REQUEST))

        request.`when`().
                post(CREATE_USER_END_POINT).
                then().
                statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo(CREATE_NEW_USER_REQUEST.name))
                .body("mobilePhoneNumber.number", equalTo(CREATE_NEW_USER_REQUEST.phoneNumber))

    }


    @Test
    internal fun `creating a user with a phone number already in use should return a conflit response code`() {

        val request = RestAssured.with()
                .contentType(ContentType.JSON)
                .body(Klaxon().toJsonString(CREATE_EXISTING_USER_REQUEST))

        request.`when`().
                post(CREATE_USER_END_POINT).
                then().
                statusCode(HttpStatus.SC_CONFLICT)
                .body(equalTo("The phone number is already in use."))
    }
}