package com.example.dragonballappavanzado.data.local

interface LocalDataSourceInterface {
    fun saveToken(token: String)
    fun getToken(): String
}