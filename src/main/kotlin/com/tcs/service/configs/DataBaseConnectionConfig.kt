package com.tcs.service.configs

import com.mongodb.ConnectionString
import org.springframework.data.mongodb.core.MongoTemplate
import com.mongodb.client.MongoClients
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.tcs.service.constant.URLPath
import com.tcs.service.constant.URLPath.SECRET_KEY1
import com.tcs.service.constant.URLPath.SECRET_KEY2
import com.tcs.service.constant.URLPath.SECRET_STORE
//import com.tcs.service.repository.EnviromentVars
import com.tcs.service.utility.Utility

import org.springframework.context.annotation.Bean



class DataBaseConnectionConfig {

    @Bean
    fun mongo(): MongoClient? {

        val connectionString = ConnectionString(Utility.getUtilitySecret(SECRET_STORE, SECRET_KEY1).toString())

        val mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build()

        return MongoClients.create(mongoClientSettings)
    }


    @Bean
    fun mongoTemplate(): MongoTemplate {

        return MongoTemplate(mongo()!!, Utility.getUtilitySecret(SECRET_STORE, SECRET_KEY2).toString())
    }
}