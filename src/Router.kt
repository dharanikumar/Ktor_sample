package com.ktor.sample

import com.ktor.sample.models.AuthenticatedUser
import com.ktor.sample.models.Feedback
import com.ktor.sample.repo.getFeedbackList
import com.ktor.sample.repo.getReview
import com.ktor.sample.repo.getUserFeedback
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.root() {
    authenticate {
        get("/") {
            call.respondText("Welcome! ${call.principal<AuthenticatedUser.User>()?.name}", ContentType.Text.Plain)
        }

        get("/feedback") {
            val user = call.principal<AuthenticatedUser.User>()
            if (user!=null) {
                val feedbackList = getUserFeedback(user,
                    { userName -> getReview(userName) },
                    { userName, reviewDate -> getFeedbackList(userName, reviewDate) })

                call.respond(feedbackList)
            }

            call.respond(emptyList<Feedback>())
        }
    }
}