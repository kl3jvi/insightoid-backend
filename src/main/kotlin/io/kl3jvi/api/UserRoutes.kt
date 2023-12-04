package io.kl3jvi.api

import io.kl3jvi.models.User
import io.kl3jvi.services.JWTService
import io.kl3jvi.services.UserService
import io.kl3jvi.utils.respondOk
import io.kl3jvi.utils.respondUnauthorized
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject
import java.util.*

fun Application.setupUserRoutes() {
    val userService: UserService by inject(UserService::class.java)
    val jwtService: JWTService by inject(JWTService::class.java)
    routing {
        userRoutes(userService, jwtService)
    }
}

fun Route.userRoutes(
    userService: UserService,
    jwtService: JWTService,
) {
    route("/auth") {
        post("/register") {
            val user = call.receive<User>()
            userService.registerUser(user)
            call.respondOk("User registered successfully")
        }

        post("/login") {
            val user = call.receive<User>()
            val loggedInUser = userService.loginUser(user.username, user.password)
            if (loggedInUser) {
                val token = jwtService.generateToken(user)
                call.respondOk("User logged in successfully", token = token)
            } else {
                call.respondUnauthorized("Invalid username or password")
            }
        }
    }
}
