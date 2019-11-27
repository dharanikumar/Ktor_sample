package com.ktor.sample.repo

import com.ktor.sample.models.*

fun getUserFeedback(
    user: User,
    getUserReview: (UserName) -> Review,
    getFeedback: (UserName, ReviewDate) -> List<Feedback>?
): List<Feedback>? {
    return getFeedback(user.name, getUserReview(user.name).reviewDate)
}