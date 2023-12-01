package io.kl3jvi

import io.kl3jvi.api.setupCrashDataRoutes
import io.kl3jvi.api.setupProjectRoutes
import io.kl3jvi.api.setupUserRoutes
import io.kl3jvi.persistence.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureKoin()
    configureDatabase()
    /*******************************************************/
    setupUserRoutes()
    setupProjectRoutes()
    setupCrashDataRoutes()

}

fun Application.configureDatabase() {
    return DatabaseFactory.init()
}

fun Application.configureKoin() {
}
