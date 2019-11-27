package com.ktor.sample.repo

import com.ktor.sample.models.Review
import com.ktor.sample.models.ReviewDate
import com.ktor.sample.models.UserName

fun getReview(userName: UserName): Review {
    return Review(reviewDate = ReviewDate(System.currentTimeMillis()))
}