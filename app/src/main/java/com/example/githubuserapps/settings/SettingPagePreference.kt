package com.example.githubuserapps.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
// Pada kode di atas, kita membuat extension properties pada Context dengan nama dataStore
// yang dibuat dengan menggunakan property delegation by preferencesDataStore. Property delegation
// adalah sebuah mekanisme untuk mendelegasikan suatu tugas kepada class lain. Dengan menggunakan
// cara ini, Anda tidak perlu tahu bagaimana cara membuat DataStore secara detail, Anda cukup
// menyerahkannya saja ke class preferencesDataStore. Selain itu, kode ini dibuat di top-level
// supaya menjadi Singleton yang cukup dipanggil sekali.

class SettingPreference private constructor(private val dataStore: DataStore<Preferences>) {

    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): kotlinx.coroutines.flow.Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }


    companion object {
        @Volatile
        //ini berfungsi sebagai singleton
        private var INSTANCE: SettingPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}