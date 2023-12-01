package io.kl3jvi.persistence

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

object Users : IntIdTable() {
    val username = varchar("username", 50).uniqueIndex()
    val hashedPassword = varchar("hashedPassword", 64)
    // Add other fields as necessary
}

object Projects : IntIdTable() {
    val user = reference("user", Users)
    val name = varchar("name", 100)
    // Add other project-related fields
}

object Crashes : IntIdTable() {
    val project = reference("project", Projects)
    val threadName = varchar("threadName", 255)
    val threadId = long("threadId")
    val exceptionName = varchar("exceptionName", 255)
    val exceptionMessage = varchar("exceptionMessage", 255)
        .default("No message provided")
    val stackTrace = text("stackTrace")
    val timestamp = datetime("timestamp")
}