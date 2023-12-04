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

    fun generateToken(user: User): Pair<String, Date> =
        JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("id", user.userId)
            .withExpiresAt(expiresAt(24 * 3_600_000))
            .sign(algorithm) to expiresAt(24 * 3_600_000)

    fun generateRefreshToken(user: User): Pair<String, Date> =
        JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("id", user.userId)
            .withExpiresAt(expiresAt(7 * 24 * 60 * 60)) // 7 days
            .sign(algorithm) to expiresAt(7 * 24 * 60 * 60)

    private fun expiresAt(time: Long) = Date(System.currentTimeMillis() + time) // 1 hour
}
