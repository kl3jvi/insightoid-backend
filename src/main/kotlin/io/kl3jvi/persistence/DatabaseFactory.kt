package io.kl3jvi.persistence

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
//        Database.connect(
//            url = "jdbc:postgresql://localhost:5432/insightoid",
//            driver = "org.postgresql.Driver",
//            user = "dbuser",
//            password = "dbpassword"
//        )
//        transaction {
//            SchemaUtils.create(Users, Projects, Crashes)
//        }
    }
}