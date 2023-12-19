package io.kl3jvi.services

import com.mongodb.reactivestreams.client.MongoCollection
import io.kl3jvi.models.CollectionType
import io.kl3jvi.models.ProjectCreation
import io.kl3jvi.models.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import org.bson.Document
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.litote.kmongo.coroutine.toList
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class ProjectService : KoinComponent {
    private val projectsCollection: MongoCollection<Document> by inject(named(CollectionType.PROJECT.name))
    private val usersCollection: MongoCollection<Document> by inject(named(CollectionType.USER.name))
    private val crashReportsCollection: MongoCollection<Document> by inject(named(CollectionType.CRASH_DATA.name))
    suspend fun createProject(
        userId: String,
        projectName: String,
        projectExistCallback: suspend () -> Unit
    ): String {
        val projectExists =
            projectsCollection.find(Document("projectName", projectName))
                .asFlow()
                .firstOrNull()
                .isNullOrEmpty()

        if (!projectExists) {
            projectExistCallback()
            return ""
        }

        val projectKey = UUID.randomUUID().toString()
        val project =
            Document("projectId", projectKey)
                .append("projectName", projectName)
                .append("userId", userId)

        projectsCollection.insertOne(project)
            .asFlow()
            .onEach { println("Inserted project with key $projectKey") }
            .catch { e -> println("Exception thrown in createProject: $e") }
            .collect()

        usersCollection.find(Document("userId", userId))
            .asFlow()
            .onEach { user ->
                val projectIds = user.getList("projectIds", String::class.java)
                projectIds.add(projectKey)
                usersCollection.updateOne(
                    Document("userId", userId),
                    Document("\$set", Document("projectIds", projectIds)),
                ).asFlow()
                    .catch { e -> println("Exception thrown in createProject: $e") }
                    .collect()
            }
            .catch { e -> println("Exception thrown in createProject: $e") }
            .collect()

        return projectKey
    }

    suspend fun getAllProjects(userId: String): List<ProjectCreation> {
        return projectsCollection.find(Document("userId", userId))
            .asFlow()
            .catch { e -> println("Exception thrown in getAllProjects: $e") }
            .map { project ->
                ProjectCreation(
                    projectName = project.getString("projectName"),
                    projectId = project.getString("projectId"),
                    userId = project.getString("userId"),
                )
            }.toList()
    }

    suspend fun getTotalCrashes(projectId: String): Int {
        return crashReportsCollection.countDocuments(Document("projectId", projectId))
            .awaitFirst()
            .toInt()
    }

    suspend fun getTotalUsers(projectId: String): Int {
        val crashReports = crashReportsCollection.find(Document("projectId", projectId)).toList()
        val uniqueIdentifiers = crashReports.map { it.getString("uniqueIdentifier") }
        return uniqueIdentifiers.distinct().size
    }

    suspend fun getUsersOnlinePast30Minutes(projectId: String): List<User> {
        val thirtyMinutesAgo = Date.from(Instant.now().minus(30, ChronoUnit.MINUTES))
        val filter = Document("lastSeen", Document("\$gte", thirtyMinutesAgo))


        return usersCollection.find(filter)
            .asFlow()
            .map { document ->
                User(
                    userId = document.getString("userId"),
                    username = document.getString("username"),
                    password = document.getString("password"),
                )
            }
            .toList()
    }
}
