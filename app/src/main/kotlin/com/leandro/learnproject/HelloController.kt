package com.leandro.learnproject

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("/hello")
class HelloController {
    private val defaultGreetings = "Hello World"
    /**
     * @return Hello World!
     */
    @Get(produces = [MediaType.TEXT_PLAIN])
    fun helloWorld(): String = defaultGreetings

    /**
     * Shows a message of greeting to given name.
     *
     * @param name The person's name
     * @return The greeting message
     */
    @Get(uri = "/{name}", produces = [MediaType.TEXT_PLAIN])
    @Operation(
        summary = "Greets a person", description = "A friendly greeting is returned",
        responses = [
            ApiResponse(content = [Content(mediaType = "text/plain", schema = Schema(type = "string"))]),
            ApiResponse(responseCode = "400", description = "Invalid Name Supplied"),
            ApiResponse(responseCode = "404", description = "Person not found")
        ]
    )
    @Tag(name = "greeting")
    fun greetings(name: String) = Single.just("Hello $name!")
}
