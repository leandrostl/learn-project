package com.leandro.learnproject

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class HelloControllerSpec {
    @Inject
    lateinit var server: EmbeddedServer

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `should retrieve Hello World`() {
        val response: String = client.toBlocking()
            .retrieve("/hello")
        Assertions.assertEquals("Hello World", response)
    }
}
