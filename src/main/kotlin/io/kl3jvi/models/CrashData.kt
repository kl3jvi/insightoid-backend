package io.kl3jvi.models

import kotlinx.serialization.Serializable

@Serializable
data class CrashData(
    val projectId: String? = null,
    val threadName: String,
    val threadId: Long,
    val exceptionName: String,
    val exceptionMessage: String,
    val stackTrace: String,
)
