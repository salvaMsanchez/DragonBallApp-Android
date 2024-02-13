package com.example.dragonballappavanzado.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.dragonballappavanzado.R
import com.example.dragonballappavanzado.databinding.ActivityLoginBinding
import com.example.dragonballappavanzado.domain.extensions.dismissKeyboard
import com.example.dragonballappavanzado.domain.extensions.loseFocusAfterAction
import com.example.dragonballappavanzado.domain.extensions.onTextChanged
import com.example.dragonballappavanzado.presentation.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    // VIEW BINDING
    private lateinit var binding: ActivityLoginBinding

    // VIEW MODEL
    private val viewModel: LoginActivityViewModel by viewModels()

    // LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isThereToken: Boolean = viewModel.onCreateView()

        if (isThereToken) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
            initUI()
        }
    }

    // FUNCTIONS
    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.viewState.collect { viewState ->
                updateUI(viewState)
            }
        }
    }

    private fun updateUI(viewState: LoginViewState) {
        when (viewState) {
            is LoginViewState.Idle -> idle()
            is LoginViewState.Error -> loginError(viewState.errorMessage)
            is LoginViewState.Loading -> showLoading(viewState.loading)
            is LoginViewState.ValidCredentials -> controlCredentialsValidation(viewState.isValidEmail, viewState.isValidPassword)
            is LoginViewState.AccessCompleted -> navigateToHome()
        }
    }

    private fun idle() { }

    private fun loginError(errorMessage: String) {
        showAuthenticationFailedDialog(errorMessage)
    }

    private fun showAuthenticationFailedDialog(errorMessage: String) {
        MaterialAlertDialogBuilder(this).apply {
            setTitle(errorMessage)
            setMessage(getString(R.string.authentication_failed))
            setPositiveButton(getString(R.string.ok), null)
            show()
        }
    }

    private fun showLoading(loading: Boolean) {
        binding.pbLoginLoading.isVisible = loading
    }

    private fun controlCredentialsValidation(isValidEmail: Boolean, isValidPassword: Boolean) {
        binding.tiEmail.error = if (isValidEmail) null else getString(R.string.login_error_email)
        binding.tiPassword.error = if (isValidPassword) null else getString(R.string.login_error_password)

        val emailText = binding.tiEmail.text
        val passwordText = binding.tiPassword.text
        if (emailText != null && passwordText != null) {
            binding.btnLogin.isVisible = isValidEmail && isValidPassword && emailText.isNotEmpty() && passwordText.isNotEmpty()
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun onFieldChanged(hasFocus: Boolean = false) {
        if (!hasFocus) {
            viewModel.onFieldsChanged(
                binding.tiEmail.text.toString(),
                binding.tiPassword.text.toString()
            )
        }
    }

    private fun initListeners() {
        binding.tiEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.tiEmail.onTextChanged { onFieldChanged() }

        binding.tiPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.tiPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.tiPassword.onTextChanged { onFieldChanged() }

        binding.btnLogin.setOnClickListener {
            it.dismissKeyboard()
            viewModel.onLoginSelected(
                binding.tiEmail.text.toString(),
                binding.tiPassword.text.toString()
            )
        }
    }
}