package com.example.dragonballappavanzado.data.remote

import com.example.dragonballappavanzado.data.remote.requests.LocationsRequest
import com.example.dragonballappavanzado.data.remote.requests.UpdateFavoriteRequest
import com.example.dragonballappavanzado.data.remote.response.CharacterRemote
import com.example.dragonballappavanzado.data.remote.response.LocationRemote

interface RemoteDataSourceInterface {
    suspend fun login(): String
    suspend fun getHeroes(): List<CharacterRemote>
    suspend fun updateFavoriteStatus(request: UpdateFavoriteRequest)
    suspend fun getLocations(request: LocationsRequest): List<LocationRemote>
}