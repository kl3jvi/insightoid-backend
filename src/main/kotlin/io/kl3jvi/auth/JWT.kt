package io.kl3jvi.auth


import io.kl3jvi.services.JWTService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.java.KoinJavaComponent.inject

fun Application.setupAuth() {
    val jwtService: JWTService by inject(JWTService::class.java)
    authentication {
        jwt {
            verifier(jwtService.verifier)
            realm = "insightoid.io"
            validate { credential ->
                if (credential.payload.getClaim("id").asString() != null)
                    JWTPrincipal(credential.payload)
                else null
            }
        }
    }
}
