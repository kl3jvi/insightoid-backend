package io.kl3jvi.api

import io.kl3jvi.models.CrashData
import io.kl3jvi.services.CrashDataService
import io.kl3jvi.utils.authPost
import io.kl3jvi.utils.respondWith
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Application.setupCrashDataRoutes() {
    val crashDataService: CrashDataService by inject(CrashDataService::class.java)
    routing {
        crashDataRoutes(crashDataService)
    }
}

fun Route.crashDataRoutes(crashDataService: CrashDataService) {
    route("/api/crash-reports") {
        authPost("/report") {
            // get project id from header, if null return 400
            val projectId: String =
                call.request.headers["projectId"]
                    ?: return@authPost call.respondWith { badRequest("Missing project id") }
            val crashData = call.receive<CrashData>()
            val changedCrashData = crashData.copy(projectId = projectId)
            crashDataService.addCrashData(changedCrashData)
            call.respondText("Crash data added successfully $crashData", status = HttpStatusCode.Created)
        }

        get("/project/{projectId}") {
            val projectId = call.parameters["projectId"]
            if (projectId == null) {
                call.respondText("Missing project id", status = HttpStatusCode.BadRequest)
            } else {
                val crashData = crashDataService.getCrashDataByProjectId(projectId)
                call.respond(crashData)
            }
        }
    }
}
