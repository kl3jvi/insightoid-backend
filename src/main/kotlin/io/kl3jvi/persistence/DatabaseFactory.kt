package io.kl3jvi.persistence

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.reactivestreams.client.MongoClients
import com.mongodb.reactivestreams.client.MongoDatabase

class DatabaseFactory {
    fun init(): MongoDatabase {
        val connectionString = /*System.getenv("MONGODB_CONNECTION_STRING")
            ?: */"mongodb+srv://kl3jvi:kl3jvi123@cluster0.bgif7au.mongodb.net/?retryWrites=true&w=majority"

        val serverApi =
            ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build()

        val mongoClientSettings =
            MongoClientSettings.builder()
                .applyConnectionString(ConnectionString(connectionString))
                .serverApi(serverApi)
                .build()

        val mongoClient =
            MongoClients
                .create(mongoClientSettings)

        return mongoClient
            .getDatabase("insightoid")
    }
}
