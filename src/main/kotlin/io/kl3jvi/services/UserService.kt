package io.kl3jvi.services

import io.kl3jvi.models.User
import io.kl3jvi.persistence.Users
import io.kl3jvi.utils.HashingUtils
import io.kl3jvi.utils.HashingUtils.verifyPassword
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserService {

    fun registerUser(username: String, password: String): User? {
        val hashedPassword = HashingUtils.hashPassword(password)
        var userId: Int? = null

        transaction {
            userId = Users.insertAndGetId {
                it[Users.username] = username
                it[Users.hashedPassword] = hashedPassword
            }.value
        }

        return userId?.let { User(it, username) }
    }

    fun loginUser(username: String, password: String): User? {
        var foundUser: User? = null

        transaction {
            Users.select { Users.username eq username }.singleOrNull()?.let {
                val hashedPassword = it[Users.hashedPassword]
                if (verifyPassword(password, hashedPassword)) {
                    foundUser = User(it[Users.id].value, username)
                }
            }
        }

        return foundUser
    }
}
