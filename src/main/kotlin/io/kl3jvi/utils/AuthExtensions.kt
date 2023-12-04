package io.kl3jvi.utils

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import java.security.SecureRandom
import java.util.*

@KtorDsl
fun Route.authGet(
    path: String,
    body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit,
) {
    authenticate {
        get(path, body)
    }
}

@KtorDsl
fun Route.authPost(
    path: String,
    body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit,
) {
    authenticate {
        post(path, body)
    }
}

fun generateJwtSecret(): String {
    val random = SecureRandom()
    val bytes = ByteArray(64) // 512 bits
    random.nextBytes(bytes)
    return Base64.getEncoder().encodeToString(bytes)
}
