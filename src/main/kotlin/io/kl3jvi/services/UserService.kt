package io.kl3jvi.services

import com.mongodb.reactivestreams.client.MongoCollection
import io.kl3jvi.models.CollectionType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.reactive.asFlow
import org.bson.Document
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.mindrot.jbcrypt.BCrypt

class UserService : KoinComponent {
    private val usersCollection: MongoCollection<Document> by inject(named(CollectionType.USER.name))

    fun registerUser(username: String, password: String) {
        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
        val user = Document("username", username)
            .append("password", hashedPassword)
            .append("projectIds", mutableListOf<String>())
        usersCollection.insertOne(user)
    }

    suspend fun loginUser(username: String, password: String): Boolean {
        return usersCollection.find(Document("username", username))
            .asFlow()
            .first { user ->
                val hashedPassword = user.getString("username")
                BCrypt.checkpw(password, hashedPassword)
            }.all {
                it.key == "username"
            }
    }
}
