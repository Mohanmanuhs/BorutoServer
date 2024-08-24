package com.example.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.LoginRequest
import com.example.security.token.TokenConfig
import com.example.util.Claim1Name
import com.example.util.Claim1Value
import com.example.util.Claim2Name
import com.example.util.LoginRoute
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

private val username = System.getenv("LoginName")
private val password = System.getenv("LoginPass")

fun Route.login(config: TokenConfig) {
    post(LoginRoute) {
        val loginRequest = call.receive<LoginRequest>()

        if (loginRequest.username == username && loginRequest.password == password) {
            val token =  JWT.create()
                .withAudience(config.audience)
                .withIssuer(config.issuer)
                .withClaim(Claim1Name, Claim1Value)
                .withClaim(Claim2Name, loginRequest.username)
                .withExpiresAt(Date(System.currentTimeMillis()+config.expiresIn))
                .sign(Algorithm.HMAC256(config.secret))
            call.respondText(token)
        } else {
            call.respondText("Invalid credentials", status = io.ktor.http.HttpStatusCode.Unauthorized)
        }
    }
}