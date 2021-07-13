package com.leandro.learnproject

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.core.convert.format.MapFormat

@ConfigurationProperties("credentials")
internal class UsersStore {

    @MapFormat
    lateinit var users: Map<String, String>

    @MapFormat
    lateinit var roles: Map<String, String>

    fun getUserPassword(username: String) : String? = users[username]
    fun getUserRole(username: String) : String? = roles[username]
}