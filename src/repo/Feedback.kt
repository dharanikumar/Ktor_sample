package com.ktor.sample.repo

import arrow.core.Either
import arrow.core.extensions.fx
import com.ktor.sample.models.*

fun getUserFeedback(
    user: User,
    getUserReview: (UserName) -> Either<ErrorMessage, Review>,
    getFeedback: (UserName, ReviewDate) -> Either<ErrorMessage, List<Feedback>>
): Either<ErrorMessage, List<Feedback>> {
    return Either.fx{
        val (review)    = getUserReview(user.name)
        val (feedback)  = getFeedback(user.name, review.reviewDate)
        feedback
    }
}