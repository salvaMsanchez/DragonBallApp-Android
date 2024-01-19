package com.example.dragonballappavanzado.presentation.main.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dragonballappavanzado.R
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI

class CharactersAdapter(
    var characters: List<CharacterUI> = emptyList(),
    private val onItemSelected: (Int) -> Unit
): RecyclerView.Adapter<CharactersViewHolder>() {
    fun updateList(characters: List<CharacterUI>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: CharactersViewHolder, position: Int) {
        viewHolder.bind(characters[position], position, onItemSelected)
    }

    override fun getItemCount(): Int = characters.size
}