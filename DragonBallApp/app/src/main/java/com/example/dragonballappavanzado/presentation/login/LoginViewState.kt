package com.example.dragonballappavanzado.presentation.login

sealed class LoginViewState {
    class Idle(): LoginViewState()
    class Error(val errorMessage: String): LoginViewState()
    class Loading(val loading: Boolean): LoginViewState()
    class ValidCredentials(val isValidEmail: Boolean, val isValidPassword: Boolean): LoginViewState()
    class AccessCompleted(val token: String): LoginViewState()
}