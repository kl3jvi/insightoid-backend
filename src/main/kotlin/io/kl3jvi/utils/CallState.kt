package io.kl3jvi.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

@Serializable
data class State(
    val status: Int,
    val message: String,
    val token: String? = null,
    @Serializable(with = DataSerializer::class)
    val data: Any? = null,
)

private suspend fun ApplicationCall.respondStatus(
    status: HttpStatusCode,
    message: String,
    token: String? = null,
    data: Any? = null,
) = respond(status, State(status.value, message, token, data))

suspend fun ApplicationCall.respondOk(
    message: String,
    token: String? = null,
    data: Any? = null,
) = respondStatus(HttpStatusCode.OK, message, token, data)

suspend fun ApplicationCall.respondCreated(
    message: String,
    data: Any?,
) = respondStatus(HttpStatusCode.Created, message)

suspend fun ApplicationCall.respondBadRequest(message: String) = respondStatus(HttpStatusCode.BadRequest, message)

suspend fun ApplicationCall.respondUnauthorized(message: String) = respondStatus(HttpStatusCode.Unauthorized, message)

suspend fun ApplicationCall.respondNotFound(message: String) = respondStatus(HttpStatusCode.NotFound, message)

suspend fun ApplicationCall.respondConflict(message: String) = respondStatus(HttpStatusCode.Conflict, message)

suspend fun ApplicationCall.respondInternalServerError(message: String) = respondStatus(HttpStatusCode.InternalServerError, message)

suspend fun ApplicationCall.respondServiceUnavailable(message: String) = respondStatus(HttpStatusCode.ServiceUnavailable, message)

suspend fun ApplicationCall.respondNotImplemented(message: String) = respondStatus(HttpStatusCode.NotImplemented, message)
