package com.example.dragonballappavanzado.data

import android.util.Log
import com.example.dragonballappavanzado.data.local.LocalDataSource
import com.example.dragonballappavanzado.data.local.sharedPreferences.SharedPreferencesService
import com.example.dragonballappavanzado.data.remote.RemoteDataSource
import okio.IOException
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {
    // FUNCTIONS
    suspend fun saveToken(token: String) = localDataSource.saveToken(token)

    suspend fun getToken(): String = localDataSource.getToken()

    suspend fun saveEmail(email: String) = localDataSource.saveEmail(email)

    suspend fun getEmail(): String = localDataSource.getEmail()

    suspend fun savePassword(password: String) = localDataSource.savePassword(password)

    suspend fun getPassword(): String = localDataSource.getPassword()

    suspend fun login(): LoginResult {
        return try {
            val token = remoteDataSource.login()
            Log.d("SALVA", "Token: $token")
            LoginResult.Success(token)
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is IOException -> "Autenticación fallida"
                else -> "Autenticación fallida"
            }
            LoginResult.Error(errorMessage)
        }
    }
}

sealed class LoginResult {
    data class Success(val token: String): LoginResult()
    data class Error(val errorMessage: String): LoginResult()
}