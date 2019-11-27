package com.ktor.sample.client

import com.ktor.sample.models.Feedback
import com.ktor.sample.models.ReviewDate
import com.ktor.sample.models.UserName

private const val FEEDBACK_API_URL = "http://localhost:3000/feedbacks"

suspend fun getFeedbackList(userName: UserName, reviewDate: ReviewDate): List<Feedback>? {
    return httpGet<List<Feedback>>("$FEEDBACK_API_URL?name=$userName")
}