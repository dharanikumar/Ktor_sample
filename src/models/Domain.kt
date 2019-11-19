package com.ktor.sample.models

data class User(var name: String)

data class Review(val reviewDate: Long)

data class Feedback(val feedbackDescription: String)