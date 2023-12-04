package io.kl3jvi

import io.kl3jvi.api.setupCrashDataRoutes
import io.kl3jvi.api.setupProjectRoutes
import io.kl3jvi.api.setupUserRoutes
import io.kl3jvi.auth.setupAuth
import io.kl3jvi.di.appModule
import io.kl3jvi.di.serviceModule
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module,
    ).start(wait = true)
}

fun Application.module() {
    startKoin {
        modules(appModule, serviceModule)
    }
    setupAuth()
    setupUserRoutes()
    setupProjectRoutes()
    setupCrashDataRoutes()
    install(ContentNegotiation) {
        json(Json {
            encodeDefaults = false
        })
    }
}
