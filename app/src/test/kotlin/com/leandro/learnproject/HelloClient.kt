package com.leandro.learnproject

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("/hello")
interface HelloClient {
    @Get(consumes = [MediaType.TEXT_PLAIN])
    fun hello(@Header authorization: String): Single<String>
}
