package com.example.dragonballappavanzado.presentation.main.characters

import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI

sealed class CharactersViewState {
    class Idle(): CharactersViewState()
    class Loading(val loading: Boolean): CharactersViewState()
    class CharactersLoaded(val characters: List<CharacterUI>): CharactersViewState()
}