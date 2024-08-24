package com.example

import com.example.plugins.*
import io.ktor.server.application.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(){
    configureKoin()  //should be first call
    configureSecurity()
    configureSerialization()
    configureRouting()
    configureMonitoring()
}
