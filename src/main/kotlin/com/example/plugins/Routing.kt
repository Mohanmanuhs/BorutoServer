package com.example.plugins

import com.example.routes.*
import com.example.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get

fun Application.configureRouting(tokenConfig: TokenConfig=get()) {
    routing {
        root()
        getAllHeroes()
        addHeroes()
        getImage()
        searchHeroes()
        login(tokenConfig)
    }
}