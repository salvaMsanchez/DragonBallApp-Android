package com.example.dragonballappavanzado.presentation.main.characterDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navArgs
import com.example.dragonballappavanzado.R
import com.example.dragonballappavanzado.databinding.ActivityCharacterDetailBinding
import com.example.dragonballappavanzado.databinding.ActivityMainBinding

class CharacterDetailActivity : AppCompatActivity() {
    // VIEW BINDING
    private lateinit var binding: ActivityCharacterDetailBinding
    // SAFE ARGS
    private val args: CharacterDetailActivityArgs by navArgs()

    // LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    // FUNCTIONS
    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.tvCharactersTitle.text = args.characterId
        binding.ivBtnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initObservers() {

    }
}