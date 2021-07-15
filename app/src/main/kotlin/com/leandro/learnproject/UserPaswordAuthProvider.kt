package com.leandro.learnproject

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationFailed
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.UserDetails
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserPaswordAuthProvider : AuthenticationProvider {
    @Inject
    lateinit var store: UsersStore

    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>?
    ): Publisher<AuthenticationResponse> {
        val username = authenticationRequest?.identity as String
        val userPassword = store.getUserPassword(username)
        if (authenticationRequest.secret != userPassword) return Flowable.just(AuthenticationFailed())

        return Flowable.just(UserDetails(username, listOf(store.getUserRole(username))))
    }
}
