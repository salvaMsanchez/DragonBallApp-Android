package com.example.dragonballappavanzado.data.mappers

import com.example.dragonballappavanzado.domain.models.CharacterLocal
import com.example.dragonballappavanzado.presentation.main.characterDetail.model.CharacterDetailUI
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI
import javax.inject.Inject

class LocalToUIMapper @Inject constructor() {
    fun mapCharacters(localCharacters: List<CharacterLocal>): List<CharacterUI> {
        return localCharacters.map { CharacterUI(it.id, it.name, it.photo) }
    }

    fun mapCharacterDetail(localCharacter: CharacterLocal): CharacterDetailUI =
        with(localCharacter) {
            CharacterDetailUI(name, photo, description, favorite)
        }
}