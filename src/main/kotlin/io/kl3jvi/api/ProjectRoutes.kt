package io.kl3jvi.api

import io.kl3jvi.models.Project
import io.kl3jvi.services.ProjectService
import io.kl3jvi.utils.authGet
import io.kl3jvi.utils.authPost
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent

fun Application.setupProjectRoutes() {
    val projectService: ProjectService by KoinJavaComponent.inject(ProjectService::class.java)
    routing { projectRoutes(projectService) }
}

fun Route.projectRoutes(projectService: ProjectService) {
    route("/projects") {
        authPost("/createProject") {
            val project = call.receive<Project>()
            projectService.createProject(project.userId, project.projectName)
            call.respondText("Project created successfully", status = HttpStatusCode.Created)
        }

        authGet("/all") {
            val projects = projectService.getAllProjects()
            call.respond(projects)
        }
    }
}
