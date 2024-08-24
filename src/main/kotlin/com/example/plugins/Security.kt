package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.security.token.TokenConfig
import com.example.util.Claim1Name
import com.example.util.Claim1Value
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.ktor.ext.get


private val jwtRealm = System.getenv("JWT_REALM")
private val auth = System.getenv("Auth")
fun Application.configureSecurity(config: TokenConfig =get()) {
    authentication {
        jwt(auth) {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(config.secret))
                    .withAudience(config.audience)
                    .withIssuer(config.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim(Claim1Name).asString() == Claim1Value)
                    JWTPrincipal(credential.payload)
                else null
            }
        }
    }
}