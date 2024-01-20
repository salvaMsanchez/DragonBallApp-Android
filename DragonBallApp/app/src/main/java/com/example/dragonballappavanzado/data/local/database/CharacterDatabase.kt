package com.example.dragonballappavanzado.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dragonballappavanzado.domain.models.CharacterLocal

@Database(entities = [CharacterLocal::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDAO
}