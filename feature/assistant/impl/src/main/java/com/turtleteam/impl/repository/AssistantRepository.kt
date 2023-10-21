package com.turtleteam.impl.repository

import com.turtleteam.api.data.api.repository.AssistantRepository
import com.turtleteam.impl.model.Answer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
class AssistantRepositoryImpl(
    private val networkDataSource: AssistantNetworkDataSource
): AssistantRepository {
    val json = Json {
        encodeDefaults = true
        explicitNulls = false
        ignoreUnknownKeys = true
        isLenient = true
    }
    override suspend fun sendMessage(message: String): String {
        return json.decodeFromString<Answer>(networkDataSource.sendMessage(message)).answer
    }
}