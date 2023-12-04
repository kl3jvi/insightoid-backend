package io.kl3jvi.models

import kotlinx.serialization.Serializable

@Serializable
data class ProjectCreation(
    val projectName: String,
    val userId: String? = null,
    val projectId: String? = null,
)

// json representation of ProjectCreation class
// {
//     "projectName": "kl3jvi",
//     "userId": "ac1c3a3c-0c5a-4e1a-8b1a-9b0b1b9b0b1b"
// }
