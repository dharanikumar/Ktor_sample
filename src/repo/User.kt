package com.ktor.sample.repo

import arrow.core.Either
import com.ktor.sample.client.httpGet
import com.ktor.sample.models.ErrorMessage
import com.ktor.sample.models.User
import io.ktor.auth.Principal
import io.ktor.auth.UserPasswordCredential

private const val AUTH_URL = "http://localhost:3000/users"

data class AuthUser(val user: User) : Principal

suspend fun verifyUser(credentials: UserPasswordCredential): Either<ErrorMessage, AuthUser> {

    val sherpaUsers = httpGet<List<User>>(AUTH_URL + "?id=" + credentials.name)

    return if (sherpaUsers != null && sherpaUsers.isNotEmpty())
        Either.right(AuthUser(sherpaUsers[0]))
    else
        Either.left(ErrorMessage("User not found"))
}