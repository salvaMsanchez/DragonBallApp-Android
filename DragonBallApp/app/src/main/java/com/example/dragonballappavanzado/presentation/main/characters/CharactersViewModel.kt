package com.example.dragonballappavanzado.presentation.main.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballappavanzado.data.Repository
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    // STATES
    private val _viewState = MutableStateFlow<CharactersViewState>(CharactersViewState.Idle())
    val viewState: StateFlow<CharactersViewState> = _viewState

    // FUNCTIONS
    fun onViewAppear() {
        viewModelScope.launch {
            _viewState.value = CharactersViewState.Loading(true)
            withContext(dispatcher) {
                val characters = repository.getCharacters()
                _viewState.value = CharactersViewState.CharactersLoaded(characters)
            }
            _viewState.value = CharactersViewState.Loading(false)
        }
    }
}