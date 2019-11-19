package com.ktor.sample.handlers

import com.ktor.sample.client.getFeedbackList
import com.ktor.sample.models.Feedback
import com.ktor.sample.models.User
import com.ktor.sample.repo.getReview
import com.ktor.sample.repo.getUserFeedback
import kotlinx.coroutines.runBlocking


fun getFeedback(user: User): List<Feedback>? {
    return getUserFeedback(user,
        { userName -> getReview(userName) },
        { userName, reviewDate ->
            runBlocking {
                getFeedbackList(userName, reviewDate)
            }
        })
}