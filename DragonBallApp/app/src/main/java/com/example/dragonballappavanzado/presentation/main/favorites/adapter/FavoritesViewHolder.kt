package com.example.dragonballappavanzado.presentation.main.favorites.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dragonballappavanzado.databinding.ItemCharacterBinding
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI

class FavoritesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    // VIEW BINDING
    private var binding = ItemCharacterBinding.bind(view)

    fun bind(character: CharacterUI) {
        binding.tvCharacterName.text = character.name
        binding.ivCharacter.load(character.photo)
    }
}