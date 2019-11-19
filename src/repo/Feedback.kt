package com.ktor.sample.repo

import com.ktor.sample.client.httpGet
import com.ktor.sample.models.Feedback
import com.ktor.sample.models.Review
import com.ktor.sample.models.SherpaUser

private const val FEEBACK_API_URL = "http://localhost:3000/feedbacks"

suspend fun getFeedbackList(userName: String, reviewDate: Long): List<Feedback>? {
    return httpGet<List<Feedback>>("$FEEBACK_API_URL?name=$userName")
}

fun getUserFeedback(
    user: SherpaUser,
    getUserReview: (String) -> Review,
    getFeedback: (String, Long) -> List<Feedback>?
): List<Feedback>? {
    return getFeedback(user.name, getUserReview(user.name).reviewDate)
}