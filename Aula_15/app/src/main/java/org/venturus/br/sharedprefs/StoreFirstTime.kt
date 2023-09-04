package org.venturus.br.sharedprefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class StoreFirstTime(private val context: Context) {

    val isFirstTime: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_FIRST_TIME_APP] ?: true
        }.take(1)

    suspend fun updateFirstTime() {
        context.dataStore.edit { settings ->
            settings[KEY_FIRST_TIME_APP] = false
        }
    }

    companion object {
        private val KEY_FIRST_TIME_APP = booleanPreferencesKey("first_time_app")
    }
}