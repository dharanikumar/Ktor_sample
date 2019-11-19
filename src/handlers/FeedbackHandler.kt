package com.ktor.sample.handlers

import com.ktor.sample.models.Feedback
import com.ktor.sample.models.SherpaUser
import com.ktor.sample.repo.getFeedbackList
import com.ktor.sample.repo.getReview
import com.ktor.sample.repo.getUserFeedback
import kotlinx.coroutines.runBlocking

object FeedbackHandler {

    fun getFeedback(user: SherpaUser): List<Feedback>? {
        return getUserFeedback(user,
            { userName -> getReview(userName) },
            { userName, reviewDate ->
                runBlocking {
                    getFeedbackList(userName, reviewDate)
                }
            })
    }
}