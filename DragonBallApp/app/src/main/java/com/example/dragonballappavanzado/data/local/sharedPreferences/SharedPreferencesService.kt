package com.example.dragonballappavanzado.data.local.sharedPreferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesService @Inject constructor(@ApplicationContext private val context: Context) {
    // PROPERTIES
    private val PREFS_NAME = "DragonBallAvanzado"
    private val TOKEN_KEY = "tokenKey"
    private val EMAIL_KEY = "emailKey"
    private val PASSWORD_KEY = "passwordKey"

    private val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // FUNCTIONS
    fun getEmail(key: String = EMAIL_KEY, defaultValue: String = "No Email"): String {
        return sharedPref.getString(key, defaultValue) ?: defaultValue
    }

    fun saveEmail(context: Context, key: String = EMAIL_KEY, characters: String) {
        with (sharedPref.edit()) {
            putString(key, characters)
            commit()
        }
    }

    fun getPassword(key: String = PASSWORD_KEY, defaultValue: String = "No Password"): String {
        return sharedPref.getString(key, defaultValue) ?: defaultValue
    }

    fun savePassword(context: Context, key: String = PASSWORD_KEY, characters: String) {
        with (sharedPref.edit()) {
            putString(key, characters)
            commit()
        }
    }

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