package io.kl3jvi.api

import io.kl3jvi.models.User
import io.kl3jvi.services.JWTService
import io.kl3jvi.services.UserService
import io.kl3jvi.utils.respondWith
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Application.setupUserRoutes() {
    val userService: UserService by inject(UserService::class.java)
    val jwtService: JWTService by inject(JWTService::class.java)
    routing { userRoutes(userService, jwtService) }
}

fun Route.userRoutes(
    userService: UserService,
    jwtService: JWTService,
) {
    route("/api/auth") {
        post("/register") {
            val user = call.receive<User>()
            userService.registerUser(user) {
                call.respondWith { conflict("User already exists") }
                return@registerUser
            }
            call.respondWith { created("User registered successfully") }
        }

        post("/login") {
            val user = call.receive<User>()
            val loggedInUser = userService.loginUser(user.username, user.password)
            if (loggedInUser) {
                val authToken = jwtService.generateToken(user)
                val refreshToken = jwtService.generateRefreshToken(user)
                userService.saveRefreshToken(user.userId, refreshToken.first)
                call.respondWith {
                    ok(
                        "User logged in successfully",
                        authToken = authToken.first,
                        refreshToken = refreshToken.first,
                        expiresAt = authToken.second,
                    )
                }
            } else {
                call.respondWith { unauthorized("Invalid username or password") }
            }
        }

        post("/refresh") {
            val refreshToken = call.receiveText()
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("id")?.asString()
            if (userId != null && userService.validateRefreshToken(userId, refreshToken)) {
                val user =
                    userService.getUserById(userId) ?: return@post call.respondWith { unauthorized("Invalid User") }
                val newToken = jwtService.generateToken(user)
                call.respondWith {
                    ok("Access token refreshed successfully", authToken = newToken.first, expiresAt = newToken.second)
                }
            } else {
                call.respondWith { unauthorized("Invalid refresh token") }
            }
        }
    }
}
