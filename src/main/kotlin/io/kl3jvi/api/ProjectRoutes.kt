package io.kl3jvi.api

import io.kl3jvi.services.ProjectService
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.setupProjectRoutes() {
    val projectService = ProjectService()
    routing {
        projectRoutes(projectService)
    }
}

fun Route.projectRoutes(projectService: ProjectService) {
    route("/projects") {
        post("/") {
            // Handle creating a new project
        }

        get("/") {
            // Handle listing all projects for a user
        }

        // Add other project-related routes
    }
}