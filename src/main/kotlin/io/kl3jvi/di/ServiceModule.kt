package io.kl3jvi.di

import io.kl3jvi.models.CollectionType
import io.kl3jvi.services.CrashDataService
import io.kl3jvi.services.ProjectService
import io.kl3jvi.services.UserService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val serviceModule = module {
    single { ProjectService() }
    single { CrashDataService() }
    single { UserService() }
}

