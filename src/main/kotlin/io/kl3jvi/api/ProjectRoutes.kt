package io.kl3jvi.api

import io.kl3jvi.models.ProjectCreation
import io.kl3jvi.services.ProjectService
import io.kl3jvi.utils.authGet
import io.kl3jvi.utils.authPost
import io.kl3jvi.utils.respondWith
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Application.setupProjectRoutes() {
    val projectService: ProjectService by inject(ProjectService::class.java)
    routing { projectRoutes(projectService) }
}

fun Route.projectRoutes(projectService: ProjectService) {
    route("/api/projects") {
        authPost("/createProject") {
            val project = call.receive<ProjectCreation>()
            val principal = call.principal<JWTPrincipal>()
            val userId =
                principal?.payload?.getClaim("id")?.asString()
                    ?: return@authPost call.respondWith { unauthorized("Couldn't create project, check auth token") }
            val projectKey = projectService.createProject(userId, project.projectName) {
                call.respondWith { conflict("Project already exists") }
                return@createProject
            }
            call.respondWith {
                created(
                    "Project created successfully", data = ProjectCreation(
                        projectName = project.projectName,
                        projectId = projectKey,
                        userId = null,
                    )
                )
            }
        }

        authGet("/all") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload
                ?.getClaim("id")
                ?.asString()
            if (userId != null) {
                val projects = projectService.getAllProjects(userId)
                call.respondWith { ok("All projects fetched successfully", data = projects) }
            } else {
                call.respondWith { unauthorized("Couldn't fetch projects, check auth token") }
            }
        }

        authGet("/{projectId}/dashboardInfo") {
            val projectId = call.parameters["projectId"]
            if (projectId != null) {
                val totalCrashes = projectService.getTotalCrashes(projectId)
                val totalUsers = projectService.getTotalUsers(projectId)
                val usersOnline = projectService.getUsersOnlinePast30Minutes(projectId)
                call.respondWith {
                    ok(
                        message = "Dashboard info fetched successfully",
                        data = mapOf(
                            "totalCrashes" to totalCrashes,
                            "totalUsers" to totalUsers,
                            "usersOnlinePast30Minutes" to usersOnline
                        )
                    )
                }
            } else {
                call.respondWith {
                    badRequest("Project ID is required")
                }
            }
        }
    }
}
