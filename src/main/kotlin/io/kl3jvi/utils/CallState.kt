package io.kl3jvi.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

data class State(
    val status: HttpStatusCode,
    val message: String,
)

private suspend fun ApplicationCall.respondStatus(
    status: HttpStatusCode,
    message: String,
) = respond(State(status, message))

suspend fun ApplicationCall.respondOk(message: String) = respondStatus(HttpStatusCode.OK, message)

suspend fun ApplicationCall.respondCreated(message: String) = respondStatus(HttpStatusCode.Created, message)

suspend fun ApplicationCall.respondBadRequest(message: String) = respondStatus(HttpStatusCode.BadRequest, message)

suspend fun ApplicationCall.respondUnauthorized(message: String) = respondStatus(HttpStatusCode.Unauthorized, message)

suspend fun ApplicationCall.respondNotFound(message: String) = respondStatus(HttpStatusCode.NotFound, message)

suspend fun ApplicationCall.respondConflict(message: String) = respondStatus(HttpStatusCode.Conflict, message)

suspend fun ApplicationCall.respondInternalServerError(message: String) = respondStatus(HttpStatusCode.InternalServerError, message)

suspend fun ApplicationCall.respondServiceUnavailable(message: String) = respondStatus(HttpStatusCode.ServiceUnavailable, message)

suspend fun ApplicationCall.respondNotImplemented(message: String) = respondStatus(HttpStatusCode.NotImplemented, message)
