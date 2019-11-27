package com.ktor.sample.handlers

import arrow.core.Either
import com.ktor.sample.client.getFeedbackList
import com.ktor.sample.models.ErrorMessage
import com.ktor.sample.models.Feedback
import com.ktor.sample.models.User
import com.ktor.sample.repo.getReview
import com.ktor.sample.repo.getUserFeedback
import kotlinx.coroutines.runBlocking


fun getFeedback(user: User): Either<ErrorMessage, List<Feedback>> {
    return getUserFeedback(user,
        { userName -> getReview(userName) },
        { userName, reviewDate ->
            runBlocking {
                getFeedbackList(userName, reviewDate)
            }
        })
}