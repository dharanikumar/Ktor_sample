package com.ktor.sample.repo

import com.ktor.sample.models.AuthenticatedUser
import io.ktor.auth.UserPasswordCredential

fun verifyUser(credentials: UserPasswordCredential): AuthenticatedUser.User? {
    //TODO: Add get User call
    return AuthenticatedUser.User(credentials.name)
}