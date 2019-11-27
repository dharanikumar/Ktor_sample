package com.ktor.sample.repo

import arrow.core.Either
import com.ktor.sample.models.ErrorMessage
import com.ktor.sample.models.Review
import com.ktor.sample.models.ReviewDate
import com.ktor.sample.models.UserName

fun getReview(userName: UserName): Either<ErrorMessage, Review>  = Either.right(Review(reviewDate = ReviewDate(System.currentTimeMillis())))
