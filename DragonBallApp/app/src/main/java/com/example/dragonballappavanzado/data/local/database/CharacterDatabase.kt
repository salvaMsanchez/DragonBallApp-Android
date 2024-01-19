package com.example.dragonballappavanzado.data.local.database

import androidx.room.Database
import com.example.dragonballappavanzado.domain.models.CharacterLocal

@Database(entities = [CharacterLocal::class], version = 1)
abstract class CharacterDatabase {
    abstract fun heroDao(): CharacterDAO
}