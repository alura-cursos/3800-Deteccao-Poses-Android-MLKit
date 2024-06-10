package com.alura.mexeai.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    object PreferencesKey {
        val highScore = intPreferencesKey("high_score")
        val useFrontalCamera = booleanPreferencesKey("use_frontal_camera")
    }

    suspend fun saveHighScore(score: Int) {
        dataStore.edit { edit ->
            edit[PreferencesKey.highScore] = score
        }
    }

    fun getHighScore(): Flow<Int> {
        return dataStore.data.map {
            it[PreferencesKey.highScore] ?: 0
        }
    }

    suspend fun saveUseFrontalCamera(useFrontalCamera: Boolean) {
        dataStore.edit { edit ->
            edit[PreferencesKey.useFrontalCamera] = useFrontalCamera
        }
    }

    fun getUseFrontalCamera(): Flow<Boolean> {
        return dataStore.data.map {
            it[PreferencesKey.useFrontalCamera] ?: true
        }
    }
}