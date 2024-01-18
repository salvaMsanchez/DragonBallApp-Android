package com.example.dragonballappavanzado.data.local.sharedPreferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesService @Inject constructor(@ApplicationContext private val context: Context) {
    // PROPERTIES
    private val PREFS_NAME = "DragonBallAvanzado"
    private val TOKEN_KEY = "tokenKey"

    private val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // FUNCTIONS
    fun getToken(key: String = TOKEN_KEY, defaultValue: String = "No Token"): String {
        return sharedPref.getString(key, defaultValue) ?: defaultValue
    }

    fun saveToken(key: String = TOKEN_KEY, token: String) {
        with (sharedPref.edit()) {
            putString(key, token)
            commit()
        }
    }

    fun deleteToken(context: Context, key: String = TOKEN_KEY) {
        with (sharedPref.edit()) {
            remove(key)
            commit()
        }
    }
}