package sample.ndy.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import sample.ndy.routes.customerRouting
import sample.ndy.routes.getOrderRoute
import sample.ndy.routes.listOrderRouting
import sample.ndy.routes.totalizeOrderRoute

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        customerRouting()
        listOrderRouting()
        getOrderRoute()
        totalizeOrderRoute()
    }
}
