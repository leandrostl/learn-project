package com.leandro.learnproject

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Base64
import javax.inject.Inject

@MicronautTest
internal class HelloClientSpec @Inject constructor(
    private val client: HelloClient
) {
    @Test
    fun `should retrieve Hello World`() {
        val credentials: String = Base64.getEncoder().encodeToString("leandro:sneezy".toByteArray(Charsets.UTF_8))
        val blockingGet = client.hello("Basic $credentials").blockingGet()
        assertEquals("Hello World", blockingGet)
    }
}
