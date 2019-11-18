package com.ktor.sample.repo

import com.ktor.sample.models.AuthenticatedUser
import com.ktor.sample.models.Feedback
import com.ktor.sample.models.Review


fun getFeedbackList(userName: String, reviewDate: Long): List<Feedback> {
    return listOf(Feedback("some feedback"))
}

fun getUserFeedback(user: AuthenticatedUser.User,
                    getUserReview: (String) -> Review,
                    getFeedback: (String, Long) -> List<Feedback>) : List<Feedback> {
    return getFeedback(user.name, getUserReview(user.name).reviewDate)
}