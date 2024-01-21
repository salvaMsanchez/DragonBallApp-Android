package com.example.dragonballappavanzado.utils.fakes

import com.example.dragonballappavanzado.data.remote.RemoteDataSourceInterface
import com.example.dragonballappavanzado.data.remote.requests.LocationsRequest
import com.example.dragonballappavanzado.data.remote.requests.UpdateFavoriteRequest
import com.example.dragonballappavanzado.data.remote.response.CharacterRemote
import com.example.dragonballappavanzado.data.remote.response.LocationRemote
import com.example.dragonballappavanzado.utils.generateRemoteCharacters
import com.example.dragonballappavanzado.utils.generateRemoteLocations

class FakeRemoteDataSource: RemoteDataSourceInterface {

    override suspend fun login(): String {
        return "123456789"
    }

    override suspend fun getHeroes(): List<CharacterRemote> {
        return generateRemoteCharacters(5)
    }

    override suspend fun updateFavoriteStatus(request: UpdateFavoriteRequest) { }

    override suspend fun getLocations(request: LocationsRequest): List<LocationRemote> {
        return generateRemoteLocations(5)
    }
}