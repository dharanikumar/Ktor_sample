package com.ktor.sample

import com.ktor.sample.handlers.getFeedback
import com.ktor.sample.models.Feedback
import com.ktor.sample.repo.AuthUser
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.root() {
    authenticate {
        get("/") {
            call.respondText(
                "Welcome! ${call.principal<AuthUser>()?.user?.name}", ContentType.Text.Plain
            )
        }

        get("/feedback") {
            val authUser = call.principal<AuthUser>()

            val feedbackList = authUser?.user?.let {
                getFeedback(it)
            }

            if (feedbackList != null) {
                call.respond(feedbackList)
            } else {
                call.respond(status = HttpStatusCode.NoContent, message = emptyList<Feedback>())
            }
        }
    }
}