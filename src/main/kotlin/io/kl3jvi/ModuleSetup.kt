package io.kl3jvi

import io.kl3jvi.api.setupCrashDataRoutes
import io.kl3jvi.api.setupProjectRoutes
import io.kl3jvi.api.setupUserRoutes
import io.kl3jvi.auth.setupAuth
import io.kl3jvi.di.appModule
import io.kl3jvi.di.serviceModule
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin

fun Application.module() {
    install(CORS) {
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        anyHost()
    }
    startKoin {
        modules(appModule, serviceModule)
    }
    setupAuth()
    setupUserRoutes()
    setupProjectRoutes()
    setupCrashDataRoutes()
    install(ContentNegotiation) {
        json(Json { encodeDefaults = false })
    }

}