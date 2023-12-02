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
    ) {
        val projectKey = UUID.randomUUID().toString()
        val project =
            Document("projectId", projectKey)
                .append("projectName", projectName)
                .append("userId", userId)
        projectsCollection.insertOne(project)

        usersCollection.find(Document("userId", userId))
            .asFlow()
            .onEach { user ->
                val projectIds = user.getList("projectIds", String::class.java)
                projectIds.add(projectKey)
                usersCollection.updateOne(
                    Document("userId", userId),
                    Document("\$set", Document("projectIds", projectIds)),
                )
            }
            .catch { e -> println("Exception thrown in createProject: $e") }
            .collect()
    }

    suspend fun getAllProjects(): List<ProjectCreation> {
        return projectsCollection.find()
            .asFlow()
            .onEach { project ->
                println(project)
            }
            .catch { e -> println("Exception thrown in getAllProjects: $e") }
            .map { project ->
                ProjectCreation(
                    projectName = project.getString("projectName"),
                    userId = project.getString("userId"),
                )
            }.toList()
    }
}
