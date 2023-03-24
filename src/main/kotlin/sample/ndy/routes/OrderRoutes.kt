package sample.ndy.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import sample.ndy.models.orderStorage

fun Route.listOrderRouting() {

    // list all orders
    get("/order") {
        if (orderStorage.isNotEmpty()) {
            call.respond(orderStorage)
        }
    }
}

fun Route.getOrderRoute() {
    // individual order
    get("/order/{number?}") {
        val number = call.parameters["number"] ?: return@get call.respondText(
            "missing order number",
            status = HttpStatusCode.BadRequest
        )

        val order = orderStorage.find { it.number == number } ?: return@get call.respondText(
            "no such order with number $number",
            status = HttpStatusCode.NotFound
        )

        call.respond(order)
    }
}

fun Route.totalizeOrderRoute() {
    get("/order/{id?}/total") {
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Bad Request",
            status = HttpStatusCode.NotFound
        )

        val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
            "Not Found",
            status = HttpStatusCode.NotFound
        )

        val total = order.contents.sumOf { it.price * it.amount }
        call.respond(total)
    }
}