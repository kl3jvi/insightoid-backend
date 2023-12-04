package io.kl3jvi.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val status: Int,
    val message: String,
    val authToken: String? = null,
    val refreshToken: String? = null,
    @Serializable(with = DataSerializer::class)
    val expiresAt: Any? = null,
    @Serializable(with = DataSerializer::class)
    val data: Any? = null,
)

class ResponseBuilder {
    fun ok(
        message: String,
        authToken: String? = null,
        refreshToken: String? = null,
        expiresAt: Any? = null,
        data: Any? = null
    ) = Response(HttpStatusCode.OK.value, message, authToken, refreshToken, expiresAt, data)

    fun created(message: String, data: Any?) =
        Response(HttpStatusCode.Created.value, message, data = data)

    fun created(message: String) = Response(HttpStatusCode.Created.value, message)

    fun badRequest(message: String) = Response(HttpStatusCode.BadRequest.value, message)

    fun unauthorized(message: String) = Response(HttpStatusCode.Unauthorized.value, message)

    fun notFound(message: String) = Response(HttpStatusCode.NotFound.value, message)

    fun conflict(message: String) = Response(HttpStatusCode.Conflict.value, message)

    fun internalServerError(message: String) = Response(HttpStatusCode.InternalServerError.value, message)

    fun serviceUnavailable(message: String) = Response(HttpStatusCode.ServiceUnavailable.value, message)

    fun notImplemented(message: String) = Response(HttpStatusCode.NotImplemented.value, message)

    fun custom(status: HttpStatusCode, message: String) = Response(status.value, message)

    fun custom(status: HttpStatusCode, message: String, data: Any?) = Response(status.value, message, data = data)

    fun custom(
        status: HttpStatusCode,
        message: String,
        authToken: String?,
        refreshToken: String?,
        expiresAt: Any?,
        data: Any?
    ) =
        Response(status.value, message, authToken, refreshToken, expiresAt, data)
}

suspend fun ApplicationCall.respondWith(builder: ResponseBuilder.() -> Response) {
    respond(getHTTPStatusCode(ResponseBuilder().builder().status), builder(ResponseBuilder()))
    respond(builder(ResponseBuilder()))
}

fun getHTTPStatusCode(status: Int): HttpStatusCode {
    return when (status) {
        200 -> HttpStatusCode.OK
        201 -> HttpStatusCode.Created
        400 -> HttpStatusCode.BadRequest
        401 -> HttpStatusCode.Unauthorized
        404 -> HttpStatusCode.NotFound
        409 -> HttpStatusCode.Conflict
        500 -> HttpStatusCode.InternalServerError
        503 -> HttpStatusCode.ServiceUnavailable
        501 -> HttpStatusCode.NotImplemented
        else -> HttpStatusCode.BadRequest
    }
}