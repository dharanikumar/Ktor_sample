package com.ktor.sample.models


data class UserName(val value: String)
data class ReviewDate(val value: Long)

data class User(val name: UserName)

data class Review(val reviewDate: ReviewDate)

data class Feedback(val feedbackDescription: String)

data class ErrorMessage(val value: String)