package io.kl3jvi.api

import io.kl3jvi.models.CrashData
import io.kl3jvi.services.CrashDataService
import io.kl3jvi.utils.respondWith
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Application.setupCrashDataRoutes() {
    val crashDataService: CrashDataService by inject(CrashDataService::class.java)
    routing { crashDataRoutes(crashDataService) }
}

fun Route.crashDataRoutes(crashDataService: CrashDataService) {
    route("/api/crash-reports") {
        post("/report") {
            val projectId: String = call.request.headers["projectId"]
                ?: return@post call.respondWith { badRequest("Missing project id") }
            val crashData = call.receive<CrashData>()
            val changedCrashData = crashData.copy(projectId = projectId)
            crashDataService.addCrashData(changedCrashData)
            call.respondWith {
                created("Crash data added successfully", data = changedCrashData)
            }
        }

        get("/project/{projectId}") {
            val projectId = call.parameters["projectId"]
            if (projectId == null) {
                call.respondWith { badRequest("Missing project id") }
            } else {
                val crashData = crashDataService.getCrashDataByProjectId(projectId)
                call.respondWith { ok("Crash data fetched successfully", data = crashData) }
            }
        }
    }
}
