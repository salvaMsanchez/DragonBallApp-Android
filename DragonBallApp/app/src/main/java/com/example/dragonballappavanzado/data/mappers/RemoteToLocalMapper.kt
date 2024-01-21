package com.example.dragonballappavanzado.data.mappers

import com.example.dragonballappavanzado.data.remote.response.CharacterRemote
import com.example.dragonballappavanzado.domain.models.CharacterLocal
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor() {
    fun mapCharacters(charactersRemote: List<CharacterRemote>): List<CharacterLocal> {
        return charactersRemote.map {
            CharacterLocal(
                it.id,
                it.name,
                it.photo,
                it.description,
                it.favorite
            )
        }
    }
}