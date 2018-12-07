package fr.contact.delivery.rest

import io.restassured.RestAssured
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class ServerTest {

    private val server = Server(InMemoryUsers())

    @BeforeEach
    internal fun setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4490;
        server.start()
    }

    @AfterEach
    internal fun tearDown() {
        server::stop
    }
}
