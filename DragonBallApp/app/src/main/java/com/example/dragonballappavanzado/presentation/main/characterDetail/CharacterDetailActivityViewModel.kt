package com.example.dragonballappavanzado.presentation.main.characterDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballappavanzado.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterDetailActivityViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): ViewModel() {
    // VIEW STATE
    private val _viewState = MutableStateFlow<CharacterDetailViewState>(CharacterDetailViewState.Idle())
    val viewState: StateFlow<CharacterDetailViewState> = _viewState

    // FUNCTIONS
    fun onViewAppear(characterId: String) {
        viewModelScope.launch {
            _viewState.value = CharacterDetailViewState.Loading(true)
            withContext(dispatcher) {
                val character = repository.getCharacter(characterId)
                _viewState.value = CharacterDetailViewState.CharacterLoaded(character)
            }
        }
    }

    fun onMapLoaded() {
        _viewState.value = CharacterDetailViewState.Loading(false)
    }
}