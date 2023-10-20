package com.turtleteam.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.turtleteam.api.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class SettingsImpl(private val context: Context) : Settings {

    companion object {
        private val THEME_KEY = booleanPreferencesKey("isDarkTheme")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private const val DATASTORE_NAME = "settings"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    override val theme: Flow<Boolean>
        get() = context.dataStore.data.map { prefs ->
            prefs[THEME_KEY] ?: false
        }
    private val token by lazy {
        context.dataStore.data.map {
            val value = it[TOKEN_KEY]
            if (value.isNullOrBlank()) null else value
        }
    }

    override suspend fun setTheme(value: Boolean) {
        context.dataStore.edit { settings ->
            settings[THEME_KEY] = value
        }
    }

    override suspend fun setToken(token: String?) {
        context.dataStore.edit { settings ->
            settings[TOKEN_KEY] = token ?: ""
        }
    }

    override suspend fun getToken(): String? {
        return token.first()
    }
}