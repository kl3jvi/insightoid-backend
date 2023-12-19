package io.kl3jvi.api

import io.kl3jvi.models.User
import io.kl3jvi.services.JWTService
import io.kl3jvi.services.UserService
import io.kl3jvi.utils.authGet
import io.kl3jvi.utils.authPost
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
            val authToken = jwtService.generateToken(user)
            val refreshToken = jwtService.generateRefreshToken(user)
            userService.saveRefreshToken(user.userId, refreshToken.first)
            call.respondWith {
                created(
                    "User registered successfully",
                    authToken = authToken.first,
                    refreshToken = refreshToken.first,
                    expiresAt = authToken.second,
                )
            }
        }

        post("/login") {
            val user = call.receive<User>()
            val loggedInUser = userService.loginUser(user)
            if (loggedInUser) {
                val authToken = jwtService.generateToken(user)
                val refreshToken = jwtService.generateRefreshToken(user)
                userService.saveRefreshToken(user.userId, refreshToken.first)
                val userEmail = userService.getUserById(user.userId)?.email
                call.respondWith {
                    ok(
                        "User logged in successfully",
                        authToken = authToken.first,
                        refreshToken = refreshToken.first,
                        expiresAt = authToken.second,
                        data = mapOf(
                            "userId" to user.userId,
                            "username" to user.username,
                            "email" to user.email.ifEmpty { userEmail },
                        )
                    )
                }
            } else {
                call.respondWith { unauthorized("Invalid username or password") }
            }
        }

        authGet("/user") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("id")?.asString() ?: return@authGet call.respondWith {
                unauthorized("Couldn't fetch user, check auth token")
            }
            val user = userService.getUserById(userId)
            if (user != null) {
                call.respondWith {
                    ok(
                        "User fetched successfully", data = mapOf(
                            "userId" to user.userId,
                            "username" to user.username,
                            "email" to user.email,
                        )
                    )
                }
            } else {
                call.respondWith { notFound("User not found") }
            }
        }

        post("/refresh") {
            val refreshToken = call.receiveText()
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("id")?.asString()
            if (userId != null && userService.validateRefreshToken(userId, refreshToken)) {
                val user = userService.getUserById(userId)
                    ?: return@post call.respondWith { unauthorized("Invalid User") }
                val newToken = jwtService.generateToken(user)
                call.respondWith {
                    ok("Access token refreshed successfully", authToken = newToken.first, expiresAt = newToken.second)
                }
            } else {
                call.respondWith { unauthorized("Invalid refresh token") }
            }
        }

        put("/user/{userId}/lastSeen") {
            val userId = call.parameters["userId"]
            if (userId != null) {
                userService.updateLastSeen(userId)
                call.respondWith { ok("User last seen updated successfully") }
            } else {
                call.respondWith { badRequest("User ID is required") }
            }
        }
    }
}
