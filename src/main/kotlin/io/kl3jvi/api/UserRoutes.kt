package io.kl3jvi.api

import io.kl3jvi.models.User
import io.kl3jvi.services.JWTService
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
    val jwtService: JWTService by inject(JWTService::class.java)
    routing {
        userRoutes(userService, jwtService)
    }
}

fun Route.userRoutes(
    userService: UserService,
    jwtService: JWTService
) {
    route("/users") {
        post("/register") {
            val user = call.receive<User>()
            userService.registerUser(user.username, user.password)
            val token = jwtService.generateToken(user)
            call.respond(mapOf("token" to token))
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