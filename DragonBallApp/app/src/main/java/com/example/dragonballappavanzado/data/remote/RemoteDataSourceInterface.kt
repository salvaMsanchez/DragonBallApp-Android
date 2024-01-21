package com.example.dragonballappavanzado.data.remote

import com.example.dragonballappavanzado.data.remote.requests.UpdateFavoriteRequest
import com.example.dragonballappavanzado.data.remote.response.CharacterRemote

interface RemoteDataSourceInterface {
    suspend fun login(): String
    suspend fun getHeroes(): List<CharacterRemote>
    suspend fun updateFavoriteStatus(request: UpdateFavoriteRequest)
}