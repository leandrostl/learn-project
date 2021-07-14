package com.leandro.learnproject

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class HelloClientSpec @Inject constructor(
    private val client: HelloClient
) {
    @Test
    fun `should retrieve Hello World`() {
        assertEquals("Hello World", client.hello().blockingGet())
    }
}
