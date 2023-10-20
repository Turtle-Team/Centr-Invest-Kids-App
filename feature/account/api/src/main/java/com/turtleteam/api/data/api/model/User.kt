package com.turtleteam.api.data.api.model

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