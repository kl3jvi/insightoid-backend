package io.kl3jvi.services

import com.mongodb.reactivestreams.client.MongoCollection
import io.kl3jvi.models.CollectionType
import io.kl3jvi.models.User
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.reactive.asFlow
import org.bson.Document
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.mindrot.jbcrypt.BCrypt

class UserService : KoinComponent {
    private val usersCollection: MongoCollection<Document> by inject(named(CollectionType.USER.name))

    suspend fun registerUser(user: User) {
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val generatedUserDocument =
            Document("username", user.username)
                .append("userId", user.userId)
                .append("password", hashedPassword)
                .append("projectIds", mutableListOf<String>())
        usersCollection.insertOne(generatedUserDocument)
            .asFlow()
            .catch { e -> println("Exception thrown in registerUser: $e") }
            .collect()
    }

    suspend fun loginUser(
        username: String,
        password: String,
    ): Boolean {
        return usersCollection.find(Document("username", username))
            .asFlow()
            .firstOrNull { user ->
                val hashedPassword = user.getString("password")
                BCrypt.checkpw(password, hashedPassword)
            } != null
    }
}
