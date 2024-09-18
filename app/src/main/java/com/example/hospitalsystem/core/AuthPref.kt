package com.example.hospitalsystem.core

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class AuthPref @Inject constructor(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        private val Context.dataStore by preferencesDataStore("user_prefs")
        val USER_TYPE = stringPreferencesKey("user_type")
    }

    val userType: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[USER_TYPE]
        }

    suspend fun setUserType(userType: String) {
        dataStore.edit { preferences ->
            preferences[USER_TYPE] = userType
        }
    }

    suspend fun clearUserType() {
        dataStore.edit { preferences ->
            preferences.remove(USER_TYPE)
        }
    }
}
