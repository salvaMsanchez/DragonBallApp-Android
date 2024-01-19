package com.example.dragonballappavanzado.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dragonballappavanzado.domain.models.CharacterLocal

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM characters")
    fun getAll(): List<CharacterLocal>

    @Insert
    fun insertAll(heros: List<CharacterLocal>)

    @Query("UPDATE characters SET favorite = :isFavorite WHERE name = :characterName")
    fun updateFavoriteStatus(characterName: String, isFavorite: Boolean)
}