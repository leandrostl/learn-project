package com.leandro.learnproject

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserPaswordAuthProvider : AuthenticationProvider {
    @Inject
    lateinit var store : UsersStore


    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>?
    ): Publisher<AuthenticationResponse> {
        val username = authenticationRequest?.identity as String
        if(authenticationRequest.secret != store.getUserPassword(username)) return Flowable.just(AuthenticationFailed())

        return Flowable.just(UserDetails(username, listOf(store.getUserRole(username))))
    }

}