package com.ktor.sample.repo

import com.ktor.sample.models.AuthenticatedUser
import io.ktor.auth.UserPasswordCredential
import io.ktor.client.HttpClient

fun verifyUser(credentials: UserPasswordCredential): AuthenticatedUser.User {
    val client = HttpClient()
//    val firstBytes = client.get<ByteArray>("http://127.0.0.1:8088/a")
    return AuthenticatedUser.User("someUser")
}