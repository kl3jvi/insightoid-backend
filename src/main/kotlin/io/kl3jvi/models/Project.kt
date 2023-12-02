package io.kl3jvi.models

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val projectName: String,
    val userId: String,
)
