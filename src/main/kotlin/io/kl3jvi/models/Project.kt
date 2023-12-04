package io.kl3jvi.models

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val projectName: String? = null,
    val projectId: String? = null,
    val crashes: List<CrashData>? = null,
)
