package com.ktor.sample.client

import com.ktor.sample.models.Feedback

private const val FEEDBACK_API_URL = "http://localhost:3000/feedbacks"

suspend fun getFeedbackList(userName: String, reviewDate: Long): List<Feedback>? {
    return httpGet<List<Feedback>>("$FEEDBACK_API_URL?name=$userName")
}