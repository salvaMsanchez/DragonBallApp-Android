package com.example.dragonballappavanzado.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dragonballappavanzado.domain.models.CharacterLocal

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM characters")
    fun getAll(): List<CharacterLocal>

    @Query("SELECT * FROM characters WHERE id = :characterId")
    fun getCharacterById(characterId: String): CharacterLocal

    @Insert
    fun insertAll(heroes: List<CharacterLocal>)

    @Query("UPDATE characters SET favorite = :isFavorite WHERE id = :characterId")
    fun updateFavoriteStatus(characterId: String, isFavorite: Boolean)
}