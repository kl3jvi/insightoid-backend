package io.kl3jvi.auth

import io.ktor.server.application.*
import io.ktor.server.auth.*


fun Application.setupJWT() {
    install(Authentication) {

    }
}