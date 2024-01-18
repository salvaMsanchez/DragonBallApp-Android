package com.example.dragonballappavanzado.data.local

import com.example.dragonballappavanzado.data.local.sharedPreferences.SharedPreferencesService
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val sharedPreferencesService: SharedPreferencesService
): LocalDataSourceInterface {

    override fun saveToken(token: String) = sharedPreferencesService.saveToken(token = token)

    override fun getToken(): String = sharedPreferencesService.getToken()
}