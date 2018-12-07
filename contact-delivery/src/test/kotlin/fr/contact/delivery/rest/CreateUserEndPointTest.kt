package fr.contact.delivery.rest

import com.beust.klaxon.Klaxon
import fr.contact.delivery.rest.requests.CreateUserRequest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test

class CreateUserEndPointTest: ServerTest() {

    @Test
    internal fun `creating a user should return a 201 response code`() {

        val request = RestAssured.with()
                .contentType(ContentType.JSON)
                .body(Klaxon().toJsonString(CreateUserRequest("test", "0192292929")))

        request.`when`().
                post("/users").
                then().
                statusCode(201)

    }
}