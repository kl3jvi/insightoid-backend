package io.kl3jvi.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CrashData(
    val projectId: String? = null,
    val threadName: String,
    val threadId: Long,
    val exceptionName: String,
    val exceptionMessage: String,
    val stackTrace: String,
    val timeStamp: Long = Date().time,
    val uniqueIdentifier: String,
)
