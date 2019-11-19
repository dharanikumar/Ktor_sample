package com.ktor.sample.repo

import com.ktor.sample.client.KtorClient
import com.ktor.sample.models.SherpaUser
import io.ktor.auth.Principal
import io.ktor.auth.UserPasswordCredential

private const val AUTH_URL = "http://localhost:3000/users"

data class AuthUser(val sherpaUser: SherpaUser) : Principal

suspend fun verifyUser(credentials: UserPasswordCredential): AuthUser? {

    val sherpaUsers = KtorClient.get<List<SherpaUser>>(AUTH_URL + "?id=" + credentials.name)

    return if (sherpaUsers != null && sherpaUsers.isNotEmpty()) AuthUser(sherpaUsers[0]) else null
}