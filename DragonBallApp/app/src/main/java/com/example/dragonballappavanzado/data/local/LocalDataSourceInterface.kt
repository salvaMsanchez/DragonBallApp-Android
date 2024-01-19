package com.example.dragonballappavanzado.data.local

interface LocalDataSourceInterface {
    fun saveToken(token: String)
    fun getToken(): String
    fun saveEmail(email: String)
    fun getEmail(): String
    fun savePassword(password: String)
    fun getPassword(): String
}