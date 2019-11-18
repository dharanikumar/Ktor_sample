package com.ktor.sample


import com.ktor.sample.repo.verifyUser
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.basic
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
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





