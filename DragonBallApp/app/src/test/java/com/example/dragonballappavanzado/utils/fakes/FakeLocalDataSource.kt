package com.example.dragonballappavanzado.utils.fakes

import com.example.dragonballappavanzado.data.local.LocalDataSourceInterface
import com.example.dragonballappavanzado.domain.models.CharacterLocal

class FakeLocalDataSource: LocalDataSourceInterface {

    private val database = mutableListOf<CharacterLocal>()
    private var token: String = "No Token"
    private var email: String = "No Email"
    private var password: String = "No Password"

    override fun getCharacters(): List<CharacterLocal> {
        return database
    }

    override fun getCharacter(characterId: String): CharacterLocal {
        return database.first()
    }

    override fun insertCharacters(characters: List<CharacterLocal>) {
        database.addAll(characters)
    }

    override fun updateFavoriteStatus(characterId: String, isFavorite: Boolean) {
        val character: CharacterLocal = database.first()
        val newCharacter: CharacterLocal = CharacterLocal(character.id, character.name, character.photo, character.description, !character.favorite)
        database.removeFirst()
        database.add(0, newCharacter)
    }

    override fun saveToken(token: String) {
        this.token = token
    }

    override fun getToken(): String {
        return token
    }

    override fun saveEmail(email: String) {
        this.email = email
    }

    override fun getEmail(): String {
        return email
    }

    override fun savePassword(password: String) {
        this.password = password
    }

    override fun getPassword(): String {
        return password
    }
}