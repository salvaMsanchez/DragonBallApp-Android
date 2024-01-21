package com.example.dragonballappavanzado.data.remote

import com.example.dragonballappavanzado.data.remote.requests.CharactersRequest
import com.example.dragonballappavanzado.data.remote.requests.LocationsRequest
import com.example.dragonballappavanzado.data.remote.requests.UpdateFavoriteRequest
import com.example.dragonballappavanzado.data.remote.response.CharacterRemote
import com.example.dragonballappavanzado.data.remote.response.LocationRemote
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: DragonBallApi
): RemoteDataSourceInterface {

    override suspend fun login(): String {
        return api.login()
    }

    override suspend fun getHeroes(): List<CharacterRemote> {
        return api.getHeroes(CharactersRequest())
    }

    override suspend fun updateFavoriteStatus(request: UpdateFavoriteRequest) {
        return api.updateFavoriteStatus(request)
    }

    override suspend fun getLocations(request: LocationsRequest): List<LocationRemote> {
        return api.getLocations(request)
    }
}