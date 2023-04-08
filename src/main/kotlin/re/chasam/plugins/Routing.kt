package re.chasam.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import re.chasam.routes.*

fun Application.configureRouting() {
    routing {
        usersRouting()
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
