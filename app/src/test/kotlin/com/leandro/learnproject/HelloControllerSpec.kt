package com.leandro.learnproject

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import javax.inject.Inject

@MicronautTest
internal class HelloControllerSpec {
    @Inject
    lateinit var server: EmbeddedServer

    @Inject
    @field:Client("/hello")
    lateinit var client: HttpClient

    @Test
    fun `should generate unauthorized status`() {
        val e = Executable {
            client.toBlocking().retrieve(HttpRequest.GET<String>("/").accept(MediaType.TEXT_PLAIN))
        }

        val thrown = assertThrows(HttpClientResponseException::class.java, e)
        assertEquals(thrown.status, HttpStatus.UNAUTHORIZED)
    }

    @Test
    fun `should generate forbidden status`() {
        val e = Executable {
            client.toBlocking().retrieve(
                HttpRequest.GET<String>("/")
                    .accept(MediaType.TEXT_PLAIN)
                    .basicAuth("admin", "grumpy"),
            )
        }

        val thrown = assertThrows(HttpClientResponseException::class.java, e)
        assertEquals(thrown.status, HttpStatus.FORBIDDEN)
    }

    @Test
    fun `should retrieve Hello World`() {
        val response: String = client.toBlocking()
            .retrieve(
                HttpRequest.GET<String>("/")
                    .accept(MediaType.TEXT_PLAIN)
                    .basicAuth("leandro", "sneezy")
            )
        assertEquals("Hello World", response)
    }
}
