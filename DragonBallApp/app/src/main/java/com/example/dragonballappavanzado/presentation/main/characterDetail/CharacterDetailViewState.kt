package com.example.dragonballappavanzado.presentation.main.characterDetail

import com.example.dragonballappavanzado.presentation.main.characterDetail.model.CharacterDetailUI
import com.example.dragonballappavanzado.presentation.main.characterDetail.model.LocationUI

sealed class CharacterDetailViewState {
    class Idle(): CharacterDetailViewState()
    class Loading(val loading: Boolean): CharacterDetailViewState()
    class CharacterLoaded(val character: CharacterDetailUI): CharacterDetailViewState()
    class LocationsLoaded(val locations: List<LocationUI>): CharacterDetailViewState()
}