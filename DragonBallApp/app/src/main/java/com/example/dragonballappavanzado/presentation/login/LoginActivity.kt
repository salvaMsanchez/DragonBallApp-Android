package com.example.dragonballappavanzado.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dragonballappavanzado.R
import com.example.dragonballappavanzado.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    // VIEW BINDING
    private lateinit var binding: ActivityLoginBinding

    // LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}