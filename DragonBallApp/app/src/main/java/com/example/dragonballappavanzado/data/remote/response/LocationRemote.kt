package com.example.dragonballappavanzado.data.remote.response

import com.squareup.moshi.Json

data class LocationRemote(
    @Json(name = "latitud") val latitude: String,
    @Json(name = "longitud") val longitude: String,
)