package fr.contact.delivery.rest

import io.restassured.RestAssured
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

open class ServerTest {

    internal val server = Server(InMemoryUsers())

    @BeforeAll
    fun setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4490;
        server.start()
    }

    @AfterAll
    fun tearDown() {
        server.stop()
    }
}
