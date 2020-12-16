package com.tcs.service.utility


import io.dapr.client.DaprClient
import io.dapr.client.DaprClientBuilder


object Utility {

    private var client: DaprClient? = null

    private fun getClientInstance(): DaprClient? {

        if(client == null){
            client = DaprClientBuilder().build()
        }

        return client
    }
    fun getUtilitySecret(secretStore: String, secretKey: String): String? {


        val mapParams: MutableMap<String, String> = mutableMapOf()
        mapParams["metadata.namespace"] = "edppublic-deliverymomentcrud-dev"


        val secret = getClientInstance()?.getSecret(secretStore,secretKey, mapParams)?.block()

        return secret?.get(secretKey)
    }

}