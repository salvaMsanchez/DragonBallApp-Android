package com.example.dragonballappavanzado.presentation.main.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dragonballappavanzado.R
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI

class FavoritesAdapter(
    private var favoriteCharacters: List<CharacterUI> = emptyList(),
): RecyclerView.Adapter<FavoritesViewHolder>() {
    fun updateList(characters: List<CharacterUI>) {
        this.favoriteCharacters = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: FavoritesViewHolder, position: Int) {
        viewHolder.bind(favoriteCharacters[position])
    }

    override fun getItemCount(): Int = favoriteCharacters.size
}