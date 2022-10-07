package com.swapcodes

import User
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation){
            json()
        }
        install(Routing) {
            homeRoute()
            urlParamRoute()
            postReq()
        }
    }.start(wait = true)
}

fun Routing.homeRoute() {
    get("/") {
        println(call.request.uri)
        println(call.request.headers.names())
        println(call.request.headers["User-Agent"])
        println(call.request.queryParameters.names())
        println("Name is ${call.request.queryParameters["name"]}")
        println("Email is ${call.request.queryParameters["email"]}")
        call.respond("Hello ktor😍")
    }
}

fun Routing.urlParamRoute() {
    val list = listOf<String>("C", "C++", "Java", "Javascript", "kotlin", "Python", "Go Lang", "Scala")
    get("/codingLang/{page}") {
        val pageNumber = call.parameters["page"]
        val fp = pageNumber!!.toInt() - 1
        call.respondText {
            if (fp > list.size - 1)
                "Not found"
            else
                "Coding language at index $fp+1 is ${list[fp]}"
        }
    }
}

fun Routing.postReq() {
    post("/login") {
        call.respondText {
            "Working..."
        }
        val user = call.receive<User>()
        println(user)
    }
}
