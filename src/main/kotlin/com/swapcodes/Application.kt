package com.swapcodes

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Routing) {
            homeRoute()
        }
    }.start(wait = true)
}

fun Routing.homeRoute() {
    get("/") {
        call.respond("Hello ktor😍")
    }
}
