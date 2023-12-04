package io.kl3jvi.services

import com.mongodb.reactivestreams.client.MongoCollection
import io.kl3jvi.models.CollectionType
import io.kl3jvi.models.ProjectCreation
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactive.asFlow
import org.bson.Document
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import java.util.*

class ProjectService : KoinComponent {
    private val projectsCollection: MongoCollection<Document> by inject(named(CollectionType.PROJECT.name))
    private val usersCollection: MongoCollection<Document> by inject(named(CollectionType.USER.name))

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
}
