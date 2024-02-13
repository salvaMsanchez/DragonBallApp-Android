package com.example.dragonballappavanzado.presentation.main.characterDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballappavanzado.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
                val locations = repository.getLocations(characterId)
                _viewState.value = CharacterDetailViewState.LocationsLoaded(locations)
            }
            withContext(dispatcher) {
                val character = repository.getCharacter(characterId)
                _viewState.value = CharacterDetailViewState.CharacterLoaded(character)
            }
            _viewState.value = CharacterDetailViewState.Loading(false)
        }
    }

    fun onFavoriteClicked(characterId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            async(dispatcher) {
                repository.updateLocalFavoriteStatus(characterId, isFavorite)
            }
            async(dispatcher) {
                repository.updateRemoteFavoriteStatus(characterId)
            }
        }
    }
}