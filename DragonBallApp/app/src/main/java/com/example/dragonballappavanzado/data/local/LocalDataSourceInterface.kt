package com.example.dragonballappavanzado.data.local

import com.example.dragonballappavanzado.domain.models.CharacterLocal

interface LocalDataSourceInterface {
    fun getCharacters(): List<CharacterLocal>
    fun insertCharacters(characters: List<CharacterLocal>)
    fun saveToken(token: String)
    fun getToken(): String
    fun saveEmail(email: String)
    fun getEmail(): String
    fun savePassword(password: String)
    fun getPassword(): String
}