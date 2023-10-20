package com.turtleteam.impl.data.api.repository

import com.turtleteam.api.data.api.model.UserDTOReceive
import com.turtleteam.api.data.api.repository.AccountRepository
import com.turtleteam.core_network.BaseRepository
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

class AccountRepositoryImpl(httpClient: HttpClient) : AccountRepository,
    BaseRepository(httpClient) {

    override suspend fun registerUser(user: UserDTOReceive) {
        val body = """
                {
                  "login": "${user.login}",
                  "password": "${user.password}"
                }
            """.trimIndent()
        executeCall(
            type = HttpMethod.Post,
            path = "user/new",
            headers = mapOf("Content-Type" to "application/json"),
            body = body
        )
    }

    override suspend fun authUser(login: String, password: String): String {
        return executeCall(
            type = HttpMethod.Get,
            path = "user/auth",
            parameters = mapOf("login" to login, "password" to password),
            headers = mapOf("Content-Type" to "application/json"),
        )
    }
}