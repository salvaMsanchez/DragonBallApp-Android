package com.example.dragonballappavanzado.data.mappers

import com.example.dragonballappavanzado.data.remote.response.CharacterRemote
import com.example.dragonballappavanzado.domain.models.CharacterLocal
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI
import javax.inject.Inject

class LocalToUIMapper @Inject constructor() {

    fun mapCharacters(localCharacters: List<CharacterLocal>): List<CharacterUI> {
        return localCharacters.map { CharacterUI(it.id, it.name, it.photo) }
    }
}