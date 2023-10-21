package com.turtleteam.impl.data.api.repository

import com.turtleteam.api.data.api.model.User
import com.turtleteam.api.data.api.model.UserDTOReceive
import com.turtleteam.api.data.api.repository.AccountRepository
import com.turtleteam.core_network.BaseRepository
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AccountRepositoryImpl(httpClient: HttpClient) : AccountRepository,
    BaseRepository(httpClient) {

    override suspend fun registerUser(user: UserDTOReceive) {
//        val body = """
//                {
//                  "login": "${user.login}",
//                  "password": "${user.password}"
//                }
//            """.trimIndent()
//        executeCall(
//            type = HttpMethod.Post,
//            path = "auth/authentication",
//            headers = mapOf("Content-Type" to "application/json"),
//            body = body
//        )
    }

    override suspend fun authUser(login: String, password: String): User {

        val body = """
                {
                  "login": "$login",
                  "password": "$password"
                }
            """.trimIndent()

        val response = executeCall(
            type = HttpMethod.Post,
            path = "auth/authentication",
            body = body,
            headers = mapOf(
                "Content-Type" to "application/json",
                "Accept" to "application/json",
            )
        )
        return Json.decodeFromString<User>(response)
    }
}