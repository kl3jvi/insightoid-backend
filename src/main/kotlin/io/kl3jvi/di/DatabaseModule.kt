package io.kl3jvi.di


import com.mongodb.reactivestreams.client.MongoDatabase
import io.kl3jvi.models.CollectionType
import io.kl3jvi.persistence.DatabaseFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single { DatabaseFactory().init() }
    single(named(CollectionType.USER.name)) { get<MongoDatabase>().getCollection("users") }
    single(named(CollectionType.PROJECT.name)) { get<MongoDatabase>().getCollection("projects") }
    single(named(CollectionType.CRASH_DATA.name)) { get<MongoDatabase>().getCollection("crashes") }
    single(named(CollectionType.EVENT.name)) { get<MongoDatabase>().getCollection("events") }
}