package com.example.dragonballappavanzado.presentation.main.favorites

import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI

sealed class FavoritesViewState {
    class Idle(): FavoritesViewState()
    class Loading(val loading: Boolean): FavoritesViewState()
    class FavoriteCharactersLoaded(val characters: List<CharacterUI>): FavoritesViewState()
}