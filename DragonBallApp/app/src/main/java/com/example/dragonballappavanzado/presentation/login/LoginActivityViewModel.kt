package com.example.dragonballappavanzado.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballappavanzado.data.LoginResult
import com.example.dragonballappavanzado.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    // COMPANION OBJECT
    private companion object {
        const val MIN_PASSWORD_LENGTH = 3
    }

    // VIEW STATE
    private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Loading(false))
    val viewState: StateFlow<LoginViewState> = _viewState

    // FUNCTIONS
    fun onCreateView(): Boolean {
        val token = repository.getToken()
        return token != "No Token" && token.isNotEmpty()
    }

    fun onLoginSelected(email: String, password: String) {
        if (isValidEmail(email) && isValidPassword(password)) {
            saveCredentials(email, password)
            loginUser(email, password)
        } else {
            onFieldsChanged(email, password)
        }
    }

    private fun saveCredentials(email: String, password: String) {
        viewModelScope.launch {
            withContext(dispatcher) {
                repository.saveEmail(email)
                repository.savePassword(password)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _viewState.value = LoginViewState.Loading(true)
            withContext(dispatcher) {
                when (val result = repository.login()) {
                    is LoginResult.Success -> {
                        Log.d("SALVA", "Llamada exitosa")
                        repository.saveToken(result.token)
                        _viewState.value = LoginViewState.AccessCompleted()
                    }
                    is LoginResult.Error -> {
                        Log.d("SALVA", "Llamada errónea")
                        _viewState.value = LoginViewState.Error(result.errorMessage)
                    }
                }
            }
            val tokenSaved = repository.getToken()
            Log.d("SALVA", "El token que hay guardado es: $tokenSaved")
            _viewState.value = LoginViewState.Loading(false)
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