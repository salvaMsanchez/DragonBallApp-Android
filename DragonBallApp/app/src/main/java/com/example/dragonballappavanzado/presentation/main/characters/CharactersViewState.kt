package com.example.dragonballappavanzado.presentation.main.characters

sealed class CharactersViewState {
    class Error(val errorMessage: String): CharactersViewState()
    class Loading(val loading: Boolean): CharactersViewState()
}