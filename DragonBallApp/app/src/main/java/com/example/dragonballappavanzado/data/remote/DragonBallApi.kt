package com.example.dragonballappavanzado.data.remote

import com.example.dragonballappavanzado.data.remote.requests.CharactersRequest
import com.example.dragonballappavanzado.data.remote.requests.LocationsRequest
import com.example.dragonballappavanzado.data.remote.requests.UpdateFavoriteRequest
import com.example.dragonballappavanzado.data.remote.response.CharacterRemote
import com.example.dragonballappavanzado.data.remote.response.LocationRemote
import retrofit2.http.Body
import retrofit2.http.POST

interface DragonBallApi {

    @POST("api/auth/login")
    suspend fun login(): String

    @POST("api/heros/all")
    suspend fun getHeroes(@Body request: CharactersRequest): List<CharacterRemote>

    @POST("api/data/herolike")
    suspend fun updateFavoriteStatus(@Body request: UpdateFavoriteRequest)

    @POST("api/heros/locations")
    suspend fun getLocations(@Body request: LocationsRequest): List<LocationRemote>
}