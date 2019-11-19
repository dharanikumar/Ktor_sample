package com.ktor.sample.repo

import com.ktor.sample.models.Feedback
import com.ktor.sample.models.Review
import com.ktor.sample.models.User

fun getUserFeedback(
    user: User,
    getUserReview: (String) -> Review,
    getFeedback: (String, Long) -> List<Feedback>?
): List<Feedback>? {
    return getFeedback(user.name, getUserReview(user.name).reviewDate)
}