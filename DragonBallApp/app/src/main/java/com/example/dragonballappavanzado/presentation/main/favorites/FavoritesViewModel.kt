package com.example.dragonballappavanzado.presentation.main.favorites

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
class FavoritesViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): ViewModel() {
    // STATES
    private val _viewState = MutableStateFlow<FavoritesViewState>(FavoritesViewState.Idle())
    val viewState: StateFlow<FavoritesViewState> = _viewState

    // FUNCTIONS
    fun onViewAppear() {
        viewModelScope.launch {
            _viewState.value = FavoritesViewState.Loading(true)
            withContext(dispatcher) {
                val favoriteCharacters = repository.getFavoriteCharacters()
                _viewState.value = FavoritesViewState.FavoriteCharactersLoaded(favoriteCharacters)
            }
            _viewState.value = FavoritesViewState.Loading(false)
        }
    }
}