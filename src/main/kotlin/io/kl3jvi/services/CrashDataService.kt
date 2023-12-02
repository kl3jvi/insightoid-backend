package io.kl3jvi.services

import com.mongodb.reactivestreams.client.MongoCollection
import io.kl3jvi.models.CollectionType
import io.kl3jvi.models.CrashData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.asFlow
import org.bson.Document
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class CrashDataService : KoinComponent {
    private val crashesCollection: MongoCollection<Document> by inject(named(CollectionType.CRASH_DATA.name))

    suspend fun addCrashData(crashData: CrashData) {
        val crashDocument =
            Document("threadName", crashData.threadName)
                .append("threadId", crashData.threadId)
                .append("exceptionName", crashData.exceptionName)
                .append("exceptionMessage", crashData.exceptionMessage)
                .append("stackTrace", crashData.stackTrace)
        crashesCollection.insertOne(crashDocument)
            .asFlow()
            .catch { e -> println("Exception thrown in addCrashData: $e") }
            .collect()
    }
}
