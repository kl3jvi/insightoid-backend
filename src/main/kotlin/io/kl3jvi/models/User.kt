package io.kl3jvi.models

import kotlinx.serialization.Serializable
import java.security.MessageDigest
import java.util.*

@Serializable
data class User(
    val email: String = "",
    val username: String,
    val password: String,
    val userId: String = generateUUIDFromUsername(username, password),
)

fun generateUUIDFromUsername(
    username: String,
    password: String,
): String {
    val toHash = username + password
    val bytes =
        MessageDigest
            .getInstance("SHA-256")
            .digest(toHash.toByteArray())
    return UUID.nameUUIDFromBytes(bytes).toString()
}
