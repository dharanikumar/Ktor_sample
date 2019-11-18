package com.ktor.sample.models

import io.ktor.auth.Principal


sealed class AuthenticatedUser : Principal {
    data class User(val name: String) : AuthenticatedUser()
}

data class Review(val reviewDate: Long)

data class Feedback(val feedbackDescription: String)