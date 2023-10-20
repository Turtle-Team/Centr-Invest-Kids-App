package com.turtleteam.api.data.api.repository

import com.turtleteam.api.data.api.model.UserDTOReceive

interface AccountRepository {

    suspend fun registerUser(user: UserDTOReceive)
    suspend fun authUser(login: String, password: String): String //TODO заменить на DTOResponse
}