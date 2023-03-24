package sample.ndy.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import sample.ndy.routes.customerRouting
import sample.ndy.routes.getOrderRoute
import sample.ndy.routes.listOrderRouting
import sample.ndy.routes.totalizeOrderRoute

fun Application.configureRouting() {
    routing {
        // get(path) is equivalent to route(path, Get) { handler {} }
        get("/") {
            call.respondText("Hello World!")
        }

        // Define a route handler
        // https://ktor.io/docs/routing-in-ktor.html#define_route
        route("/hello", HttpMethod.Get) {
            handle {
                call.respondText("World!")
            }
        }

        //authenticated end point
        authenticate("auth-jwt") {
            get("/hello-jwt") {
                val principal = call.principal<JWTPrincipal>()
                val username = principal!!.payload.getClaim("username").asString()
                val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
                call.respondText("Hello, $username! Token is expired at $expiresAt ms.")
            }
        }

        customerRouting()
        listOrderRouting()
        getOrderRoute()
        totalizeOrderRoute()
    }
}
