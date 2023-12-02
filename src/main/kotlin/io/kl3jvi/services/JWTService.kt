package io.kl3jvi.services

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.kl3jvi.models.User
import java.util.*

class JWTService(
    private val issuer: String,
    jwtSecret: String,
) {
    private val algorithm = Algorithm.HMAC512(jwtSecret)

    val verifier: JWTVerifier = JWT.require(algorithm).withIssuer(issuer).build()

    fun generateToken(user: User): String =
        JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("id", user.userId)
            .withExpiresAt(expiresAt())
            .sign(algorithm)

    private fun expiresAt() = Date(System.currentTimeMillis() + 3_600_000) // 1 hour
}
