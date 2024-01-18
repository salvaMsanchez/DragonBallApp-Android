package com.example.dragonballappavanzado.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballappavanzado.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    // COMPANION OBJECT
    private companion object {
        const val MIN_PASSWORD_LENGTH = 3
    }

    // VIEW STATE
    private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Idle())
    val viewState: StateFlow<LoginViewState> = _viewState

    // FUNCTIONS
    fun onLoginSelected(email: String, password: String) {
        loginUser(email)
    }

    private fun loginUser(email: String) {
        viewModelScope.launch {
            _viewState.value = LoginViewState.Loading(true)
            withContext(Dispatchers.IO) {
                repository.saveToken(email)
            }
            _viewState.value = LoginViewState.Loading(false)

            var tokenGot: String
            withContext(Dispatchers.IO) {
                tokenGot = repository.getToken()
            }
            Log.d("SALVA", "El token guardado y recuperado es: $tokenGot")
        }
    }

    fun onFieldsChanged(email: String, password: String) {
        _viewState.value = LoginViewState.ValidCredentials(isValidEmail(email), isValidPassword(password))
    }

    private fun isValidPassword(password: String): Boolean =
        password.length >= MIN_PASSWORD_LENGTH || password.isEmpty()

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") || email.isEmpty()
    }
}