package com.tcs.service.configs

import com.mongodb.ConnectionString
import org.springframework.data.mongodb.core.MongoTemplate
import com.mongodb.client.MongoClients
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.tcs.service.constant.URLPath.SECRET_KEY1
import com.tcs.service.constant.URLPath.SECRET_KEY2
import com.tcs.service.constant.URLPath.SECRET_STORE
import com.tcs.service.utility.Utility





object DataBaseConnectionConfig {

    var mongoClient: MongoClient? = null

    fun mongo(): MongoClient? {
        if(mongoClient == null) {
            val connectionString = ConnectionString(Utility.getUtilitySecret(SECRET_STORE, SECRET_KEY1).toString())

            val mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build()

            mongoClient =  MongoClients.create(mongoClientSettings)
        }
        return mongoClient
    }



    fun mongoTemplate(): MongoTemplate {

        return MongoTemplate(mongo()!!, Utility.getUtilitySecret(SECRET_STORE, SECRET_KEY2).toString())
    }
}