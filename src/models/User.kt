package com.thoughtworks.sherpa.models

import io.ktor.auth.Principal

sealed class AuthenticatedUser : Principal {
    data class User(val name: String) : AuthenticatedUser()
}
