package io.kl3jvi.utils

import org.mindrot.jbcrypt.BCrypt

object HashingUtils {
    fun hashPassword(password: String): String = BCrypt.hashpw(password, BCrypt.gensalt())
    fun verifyPassword(password: String, hashedPassword: String): Boolean =
        BCrypt.checkpw(password, hashedPassword)
}