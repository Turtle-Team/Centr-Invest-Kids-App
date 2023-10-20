package com.turtleteam.api

import kotlinx.coroutines.flow.Flow

interface Settings {

    val theme: Flow<Boolean>

    suspend fun setTheme(value: Boolean)
    suspend fun setToken(token: String?)

    suspend fun getToken(): String?
}