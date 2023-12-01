package io.kl3jvi.api

import io.kl3jvi.persistence.Crashes
import io.kl3jvi.services.CrashDataService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.setupCrashDataRoutes() {
    val crashDataService = CrashDataService()
    routing {
        crashDataRoutes(crashDataService)
    }
}

data class CrashData(
    val threadName: String,
    val threadId: Long,
    val exceptionName: String,
    val exceptionMessage: String? = "No message provided",
    val stackTrace: String,
)

fun Route.crashDataRoutes(crashDataService: CrashDataService) {
    route("/crashes") {
        post("/") {
            // Handle crash data upload, parsing, and storage
            val a = call.receive<CrashData>()
            call.respondText(a.stackTrace)
        }

        get("/{projectId}") {
            // Handle retrieving crash data for a specific project
        }
    }
}