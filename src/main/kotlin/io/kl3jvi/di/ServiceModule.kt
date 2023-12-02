package io.kl3jvi.di

import io.kl3jvi.auth.JWTService
import io.kl3jvi.services.CrashDataService
import io.kl3jvi.services.ProjectService
import io.kl3jvi.services.UserService
import org.koin.dsl.module

val serviceModule = module {
    single { ProjectService() }
    single { CrashDataService() }
    single { UserService() }
    single {
        JWTService(
            issuer = /*environment.config.property("jwt.domain").getString() ?: */  "ktor.io",
            jwtSecret =/* environment.config.property("jwt.secret").getString() ?:*/ "secret"
        )
    }
}

