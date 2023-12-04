package io.kl3jvi.models

data class Project(
    val projectName: String,
    val projectId: String,
    val crashes: List<CrashData>,
)
