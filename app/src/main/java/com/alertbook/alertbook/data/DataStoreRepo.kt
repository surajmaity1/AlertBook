package com.alertbook.alertbook.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "intro_pref")

class DataStoreRepo(context: Context) {

    private object PreferencesKey {
        val introKey = booleanPreferencesKey(name = "intro_completed")
    }

    private val dataStore = context.dataStore

    suspend fun saveIntroState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.introKey] = completed
        }
    }

    fun readIntroState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val introState = preferences[PreferencesKey.introKey] ?: false
                introState
            }
    }
}