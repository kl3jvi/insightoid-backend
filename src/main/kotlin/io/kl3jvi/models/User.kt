package io.kl3jvi.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: String,
    val username: String,
    val password: String,
)
