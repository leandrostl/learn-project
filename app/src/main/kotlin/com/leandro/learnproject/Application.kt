package com.leandro.learnproject

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info


@OpenAPIDefinition(
    info = Info(
        title = "Learn Project",
        version = "0.0",
        description = "A project to rule them all!! Nothing but a place to learn and try interesting technologies."
    )
)
object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.run(Application.javaClass)
    }
}
