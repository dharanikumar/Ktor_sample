package com.ktor.sample.repo

import com.ktor.sample.client.httpGet
import com.ktor.sample.models.User
import io.ktor.auth.Principal
import io.ktor.auth.UserPasswordCredential

private const val AUTH_URL = "http://localhost:3000/users"

data class AuthUser(val user: User) : Principal

suspend fun verifyUser(credentials: UserPasswordCredential): AuthUser? {

    val sherpaUsers = httpGet<List<User>>(AUTH_URL + "?id=" + credentials.name)

    return if (sherpaUsers != null && sherpaUsers.isNotEmpty()) AuthUser(sherpaUsers[0]) else null
}