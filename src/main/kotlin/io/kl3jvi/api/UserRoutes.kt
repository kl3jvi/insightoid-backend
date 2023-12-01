package io.kl3jvi.api

import io.kl3jvi.models.Project
import io.kl3jvi.models.User
import io.kl3jvi.services.ProjectService
import io.kl3jvi.services.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject


fun Application.setupUserRoutes() {
    val userService: UserService by inject(UserService::class.java)
    val projectService: ProjectService by inject(ProjectService::class.java)
    routing {
        userRoutes(userService, projectService)
    }
}

fun Route.userRoutes(userService: UserService, projectService: ProjectService) {
    route("/users") {
        post("/register") {
            val user = call.receive<User>()
            userService.registerUser(user.username, user.password)
            call.respondText("User registered successfully", status = HttpStatusCode.Created)
        }

        // create project route
        post("/createProject") {
            val user = call.receive<Project>()
            projectService.createProject(user.userId, user.projectName)
            call.respondText("Project created successfully", status = HttpStatusCode.Created)
        }

        post("/login") {
            // Handle logging in a user
            val user = call.receive<User>()
            val isUserValid = userService.loginUser(user.username, user.password)
            if (isUserValid) {
                call.respondText("User logged in successfully", status = HttpStatusCode.OK)
            } else {
                call.respondText("Invalid username or password", status = HttpStatusCode.Unauthorized)
            }
        }
    }
}