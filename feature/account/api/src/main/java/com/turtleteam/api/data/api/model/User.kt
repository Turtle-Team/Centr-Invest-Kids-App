package com.turtleteam.api.data.api.model

import kotlinx.serialization.Serializable

data class UserDTOReceive(
    val login: String,
    val password: String
)

data class UserDTOResponse(
    val id: Int,
    val login: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

@Serializable
data class User(
    val token: String,
    val user: UserInfo
)

@Serializable
data class UserInfo(
    val birthdate: String,
    val firstname: String,
    val lastname: String,
    val phone: String,
    val surname: String
)