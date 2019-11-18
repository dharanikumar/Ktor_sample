package com.ktor.sample

import com.thoughtworks.sherpa.models.AuthenticatedUser
import com.thoughtworks.sherpa.models.Feedback
import com.thoughtworks.sherpa.models.Review
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.HttpClient
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.response.*
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(){
    embeddedServer(Netty, port = 8088, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {

        }
    }
    install(Authentication) {
        basic {
            realm = "ktor"
            validate { credentials ->
                val user = verifyUser(credentials)
                if (user!=null) {
                    user
                } else {
                    null
                }
            }
        }
    }

    routing {
        root()
    }
}

fun verifyUser(credentials: UserPasswordCredential): AuthenticatedUser.User {
    val client = HttpClient()
//    val firstBytes = client.get<ByteArray>("http://127.0.0.1:8088/a")
    return AuthenticatedUser.User("someUser")
}

fun Routing.root() {
    authenticate {
        get("/") {

            call.respondText("Welcome! ${call.principal<AuthenticatedUser.User>()?.name}", ContentType.Text.Plain)
        }

        get("/feedback") {
            val user = call.principal<AuthenticatedUser.User>()
            if (user!=null) {
                val feedbackList = getUserFeedback(user,
                    { userName -> getReview(userName)},
                    { userName, reviewDate -> getFeedbackList(userName, reviewDate)
                    })

                call.respond(feedbackList)
            }

            call.respondText("Success, ${call.authentication.principal<AuthenticatedUser.User>()?.name}")
        }
    }
    get("/demo") {
        call.respondText("HELLO WORLD!")
    }
}


fun getUserFeedback(user: AuthenticatedUser.User,
                    getUserReview: (String) -> Review,
                    getFeedback: (String, Long) -> List<Feedback>) : List<Feedback> {
    return getFeedback(user.name, getUserReview(user.name).reviewDate)
}

fun getReview(userName: String): Review {
    return Review(0L)
}

fun getFeedbackList(userName: String, reviewDate: Long): List<Feedback> {
    return listOf(Feedback("some feedback"))
}

