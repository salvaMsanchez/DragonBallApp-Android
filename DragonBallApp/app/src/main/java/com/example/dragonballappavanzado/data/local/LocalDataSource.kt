package com.example.dragonballappavanzado.data.local

import com.example.dragonballappavanzado.data.local.database.CharacterDAO
import com.example.dragonballappavanzado.data.local.sharedPreferences.SharedPreferencesService
import com.example.dragonballappavanzado.domain.models.CharacterLocal
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val sharedPreferencesService: SharedPreferencesService,
    private val dao: CharacterDAO,
): LocalDataSourceInterface {
    override fun getCharacters(): List<CharacterLocal> {
        return dao.getAll()
    }

    override fun insertCharacters(characters: List<CharacterLocal>) {
        return dao.insertAll(characters)
    }

    override fun saveToken(token: String) = sharedPreferencesService.saveToken(token = token)

    override fun getToken(): String = sharedPreferencesService.getToken()

    override fun saveEmail(email: String) = sharedPreferencesService.saveEmail(email = email)

    override fun getEmail(): String = sharedPreferencesService.getEmail()

    override fun savePassword(password: String) = sharedPreferencesService.savePassword(password = password)

    override fun getPassword(): String = sharedPreferencesService.getPassword()
}