package io.kl3jvi.services

import com.mongodb.reactivestreams.client.MongoCollection
import io.kl3jvi.models.CollectionType
import io.kl3jvi.models.CrashData
import io.kl3jvi.models.Project
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactive.asFlow
import org.bson.Document
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class CrashDataService : KoinComponent {
    private val crashesCollection: MongoCollection<Document> by inject(named(CollectionType.CRASH_DATA.name))
    private val projectCollection: MongoCollection<Document> by inject(named(CollectionType.PROJECT.name))

    suspend fun addCrashData(crashData: CrashData) {
        val crashDocument = Document("projectId", crashData.projectId)
            .append("threadName", crashData.threadName)
            .append("threadId", crashData.threadId)
            .append("exceptionName", crashData.exceptionName)
            .append("exceptionMessage", crashData.exceptionMessage)
            .append("stackTrace", crashData.stackTrace)

        crashesCollection.insertOne(crashDocument)
            .asFlow()
            .catch { e ->
                println("Exception thrown in addCrashData: $e")
            }.collect()
    }

    suspend fun getCrashDataByProjectId(projectId: String): Project {
        val projectDocument = projectCollection.find(Document("projectId", projectId)).asFlow().first()
        val projectName = projectDocument.getString("projectName")

        val crashes =
            crashesCollection.find(Document("projectId", projectId)).asFlow().map {
                CrashData(
                    projectId = it.getString("projectId"),
                    threadName = it.getString("threadName"),
                    threadId = it.getLong("threadId"),
                    exceptionName = it.getString("exceptionName"),
                    exceptionMessage = it.getString("exceptionMessage"),
                    stackTrace = it.getString("stackTrace"),
                )
            }.toList()
        return Project(projectName, projectId, crashes)
    }
}
