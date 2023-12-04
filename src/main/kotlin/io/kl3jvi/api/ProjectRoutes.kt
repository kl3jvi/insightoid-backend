package io.kl3jvi.api

import io.kl3jvi.models.ProjectCreation
import io.kl3jvi.services.ProjectService
import io.kl3jvi.utils.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
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
            val project = call.receive<ProjectCreation>()
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("id")?.asString()
                ?: return@authPost call.respondUnauthorized("Couldn't create project, check auth token")
            projectService.createProject(userId, project.projectName)
            call.respondCreated("Project created successfully", data = project)
        }

        authGet("/all") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("id")?.asString()
            if (userId != null) {
                val projects = projectService.getAllProjects(userId)
                call.respondOk("All projects fetched successfully", data = projects)
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Unauthorized")
            }
        }
    }
}
