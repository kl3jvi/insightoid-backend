package io.kl3jvi.models

import kotlinx.serialization.Serializable

@Serializable
data class ProjectCreation(
    val projectName: String,
    val userId: String,
)
