package com.siradze.route

import com.siradze.route.models.host.HostRequest
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.slf4j.event.Level

fun Application.configureRouting() {
    install(ContentNegotiation) {
        json()
    }
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)
        }
    }

    val controller by inject<RouteController>()
    routing {
        get("/") {
            throw Exception("We are doing too good!")
        }
        post("/host") {
            val hostRequest = call.receive<HostRequest>()
            call.respond(controller.host(hostRequest))
        }
        get("/servers") {
            call.respond(controller.servers())
        }
        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
    }
}