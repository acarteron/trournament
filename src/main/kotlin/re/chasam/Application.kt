package re.chasam

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import re.chasam.di.tournamentModule
import re.chasam.plugins.*

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val port = environment?.config?.propertyOrNull("database.port")?.getString()?.toInt()
    install(Koin) {
        slf4jLogger()
        modules(tournamentModule)
    }
    configureSerialization()
    configureRouting()
}
