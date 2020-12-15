package com.tcs.service.utility

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.dapr.client.DaprClient
import io.dapr.client.DaprClientBuilder

import khttp.get
import kotlin.reflect.typeOf

object Utility {

    fun getUtilitySecret(secretStore: String, secretKey: String): String? {

        val client : DaprClient = DaprClientBuilder().build()
        println(client)
        var mapParams: MutableMap<String, String> = mutableMapOf<String, String>()
        mapParams.put("metadata.namespace", "edppublic-deliverymomentcrud-dev")

//        var secret = client.getSecret(secretStore,secretKey).block()
        var secret = client.getSecret(secretStore,secretKey, mapParams).block()

        return secret?.get(secretKey.toString())
    }


}