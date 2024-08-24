package com.example.di

import com.example.data.Datasource
import com.example.data.MongoDatasourceImpl
import com.example.security.token.TokenConfig
import com.example.util.ExpiresIn
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.koin.dsl.module

private val mongoPw: String = System.getenv("MONGO_PW")
private val dbName = System.getenv("Db_Name")
private val mongoUserName = System.getenv("UserName")
private val ConnectionString = "mongodb+srv://$mongoUserName:$mongoPw@cluster0.uouah8w.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"


val pojoCodecRegistry: CodecRegistry = CodecRegistries.fromRegistries(
    MongoClientSettings.getDefaultCodecRegistry(),
    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
)

val koinModule= module {
    single<TokenConfig> {
        TokenConfig(
            issuer = System.getenv("Issuer"),
            audience = System.getenv("Audience"),
            expiresIn = ExpiresIn,
            secret = System.getenv("JWT_SECRET")
        )
    }

    single<Datasource> {
        MongoDatasourceImpl(get())
    }

    single<MongoDatabase> {
        MongoClients.create(
            MongoClientSettings.builder()
                .applyConnectionString(ConnectionString(ConnectionString))
                .codecRegistry(pojoCodecRegistry)
                .build()
        ).getDatabase(dbName)
    }

}