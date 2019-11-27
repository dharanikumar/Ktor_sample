package com.ktor.sample.client

import arrow.core.Either
import com.ktor.sample.models.ErrorMessage
import com.ktor.sample.models.Feedback
import com.ktor.sample.models.ReviewDate
import com.ktor.sample.models.UserName
import java.lang.Exception

private const val FEEDBACK_API_URL = "http://localhost:3000/feedbacks"

suspend fun getFeedbackList(userName: UserName, reviewDate: ReviewDate): Either<ErrorMessage, List<Feedback>> {
        val feedback = httpGet<List<Feedback>>("$FEEDBACK_API_URL?name=$userName")
         return if (feedback!=null)
                    Either.right(feedback)
                else
                    Either.left(ErrorMessage("No Feedback"))
}