package sample.ndy.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import sample.ndy.config.JwtConfig

fun Application.configureJwt() {
    install(Authentication) {
        jwt("auth-jwt") {
            verifier(JwtConfig.verifier)
            realm = "Access to sample ktor app"
            validate { JWTPrincipal(it.payload) }
        }
    }
}