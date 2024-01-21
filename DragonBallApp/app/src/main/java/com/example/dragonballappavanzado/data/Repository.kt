package com.example.dragonballappavanzado.data

import android.util.Log
import com.example.dragonballappavanzado.data.local.LocalDataSource
import com.example.dragonballappavanzado.data.local.sharedPreferences.SharedPreferencesService
import com.example.dragonballappavanzado.data.mappers.LocalToUIMapper
import com.example.dragonballappavanzado.data.mappers.RemoteToLocalMapper
import com.example.dragonballappavanzado.data.remote.RemoteDataSource
import com.example.dragonballappavanzado.data.remote.response.CharacterRemote
import com.example.dragonballappavanzado.domain.models.CharacterLocal
import com.example.dragonballappavanzado.presentation.main.characterDetail.model.CharacterDetailUI
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI
import okio.IOException
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val localToUIMapper: LocalToUIMapper,
    private val remoteToLocalMapper: RemoteToLocalMapper,
) {
    // FUNCTIONS
    fun saveToken(token: String) = localDataSource.saveToken(token)

    fun getToken(): String = localDataSource.getToken()

    fun saveEmail(email: String) = localDataSource.saveEmail(email)

    //suspend fun getEmail(): String = localDataSource.getEmail()

    fun savePassword(password: String) = localDataSource.savePassword(password)

    //suspend fun getPassword(): String = localDataSource.getPassword()

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

    suspend fun getCharacters(): List<CharacterUI> {
        val localCharacters: List<CharacterLocal> = localDataSource.getCharacters()

        return if (localCharacters.isNotEmpty()) {
            Log.d("SALVA", "Characters recuperados de BBDD")
            localToUIMapper.mapCharacters(localCharacters)
        } else {
            Log.d("SALVA", "Characters recuperados de API")
            val remoteCharacters: List<CharacterRemote> = remoteDataSource.getHeroes()
            val charactersCleaned: List<CharacterRemote> = remoteCharacters.dropLast(1)
            localDataSource.insertCharacters(remoteToLocalMapper.mapCharacters(charactersCleaned))

            val updatedLocalCharacters: List<CharacterLocal> = localDataSource.getCharacters()
            localToUIMapper.mapCharacters(updatedLocalCharacters)
        }
    }

    suspend fun getCharacter(characterId: String): CharacterDetailUI {
        val localCharacter = localDataSource.getCharacter(characterId)
        return localToUIMapper.mapCharacterDetail(localCharacter)
    }
}

sealed class LoginResult {
    data class Success(val token: String): LoginResult()
    data class Error(val errorMessage: String): LoginResult()
}