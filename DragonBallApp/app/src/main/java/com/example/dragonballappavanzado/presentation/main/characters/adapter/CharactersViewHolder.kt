package com.example.dragonballappavanzado.presentation.main.characters.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dragonballappavanzado.databinding.ItemCharacterBinding
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI

class CharactersViewHolder(view: View): RecyclerView.ViewHolder(view) {
    // VIEW BINDING
    private var binding = ItemCharacterBinding.bind(view)

    fun bind(character: CharacterUI, characterId: String, onItemSelected: (String) -> Unit) {
        binding.tvCharacterName.text = character.name
        binding.ivCharacter.load(character.photo)
        binding.root.setOnClickListener {onItemSelected(characterId) }
    }
}