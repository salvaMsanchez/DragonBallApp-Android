package com.example.dragonballappavanzado.data

import com.example.dragonballappavanzado.data.local.LocalDataSource
import com.example.dragonballappavanzado.data.local.sharedPreferences.SharedPreferencesService
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    // FUNCTIONS
    suspend fun saveToken(token: String) = localDataSource.saveToken(token)

    suspend fun getToken(): String = localDataSource.getToken()
}