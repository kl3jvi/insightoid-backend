package io.kl3jvi.di

import io.kl3jvi.services.CrashDataService
import io.kl3jvi.services.JWTService
import io.kl3jvi.services.ProjectService
import io.kl3jvi.services.UserService
import org.koin.dsl.module

val serviceModule =
    module {
        single { ProjectService() }
        single { CrashDataService() }
        single { UserService() }
        single {
            JWTService(
                issuer = /*environment.config.property("jwt.domain").getString() ?: */ "insightoid.io",
                jwtSecret = /* environment.config.property("jwt.secret").getString() ?:*/ "secret",
            )
        }
    }
