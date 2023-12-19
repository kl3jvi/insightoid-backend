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
import java.util.*

class UserService : KoinComponent {
    private val usersCollection: MongoCollection<Document> by inject(named(CollectionType.USER.name))
    suspend fun registerUser(
        user: User,
        userExist: suspend () -> Unit,
    ) {
        // check if there is already a user with the same username or email
        val existingUser =
            usersCollection.find(
                Document(
                    "\$or", listOf(
                        Document("username", user.username),
                        Document("email", user.email)
                    )
                )
            )
                .asFlow()
                .firstOrNull()
                .isNullOrEmpty()

        if (!existingUser) {
            userExist()
            return
        }

        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val generatedUserDocument =
            Document("userId", user.userId)
                .append("username", user.username)
                .append("email", user.email)
                .append("password", hashedPassword)
                .append("projectIds", mutableListOf<String>())
        usersCollection.insertOne(generatedUserDocument)
            .asFlow()
            .catch { e -> println("Exception thrown in registerUser: $e") }
            .collect()
    }

    suspend fun loginUser(user: User): Boolean {
        // Create a filter to find a user with the given username or email
        val filter = Document("\$or", listOf(Document("username", user.username), Document("email", user.username)))

        return usersCollection.find(filter)
            .asFlow()
            .firstOrNull { foundUser ->
                val hashedPassword = foundUser.getString("password")
                BCrypt.checkpw(user.password, hashedPassword)
            } != null
    }

    suspend fun saveRefreshToken(
        userId: String,
        refreshToken: String,
    ) {
        usersCollection.updateOne(
            Document("userId", userId),
            Document("\$set", Document("refreshToken", refreshToken)),
        ).asFlow()
            .catch { e -> println("Exception thrown in saveRefreshToken: $e") }
            .collect()
    }

    suspend fun validateRefreshToken(
        userId: String,
        refreshToken: String,
    ): Boolean {
        val user = usersCollection.find(Document("userId", userId)).asFlow().firstOrNull()
        return user?.getString("refreshToken") == refreshToken
    }

    suspend fun getUserById(userId: String): User? {
        val userDocument = usersCollection.find(Document("userId", userId)).asFlow().firstOrNull()
        return userDocument?.let {
            User(
                userId = it.getString("userId"),
                username = it.getString("username"),
                password = it.getString("password"),
                email = it.getString("email"),
            )
        }
    }

    suspend fun updateLastSeen(userId: String) {
        val update = Document("\$set", Document("lastSeen", Date()))
        usersCollection.updateOne(Document("userId", userId), update)
            .asFlow()
            .catch { e -> println("Exception thrown in updateLastSeen: $e") }
            .collect()
    }


}
