package com.ktor.sample.client

import io.ktor.client.HttpClient
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get


suspend inline fun <reified T> httpGet(url: String): T? {
    val client = HttpClient {
        install(JsonFeature) {
            serializer = GsonSerializer {
                serializeNulls()
            }
        }
    }

    return client.get(url)
}