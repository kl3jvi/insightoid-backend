package io.kl3jvi.api

import io.kl3jvi.models.CrashData
import io.kl3jvi.services.CrashDataService
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
    route("/crashes") {
        post("/") {
            // get project id from header, if null return 400
            val projectId: String =
                call.request.headers["apiKey"]
                    ?: return@post call.respondText("Missing project id", status = HttpStatusCode.BadRequest)

            val crashData = call.receive<CrashData>()
            crashDataService.addCrashData(crashData.copy(projectId = projectId))
            call.respondText("Crash data added successfully $crashData", status = HttpStatusCode.Created)
        }

        get("/{projectId}") {
            // Handle retrieving crash data for a specific project
        }
    }
}
