package com.example.dragonballappavanzado.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.dragonballappavanzado.data.local.LocalDataSource
import com.example.dragonballappavanzado.data.local.LocalDataSourceInterface
import com.example.dragonballappavanzado.data.local.database.CharacterDAO
import com.example.dragonballappavanzado.data.local.database.CharacterDatabase
import com.example.dragonballappavanzado.data.local.sharedPreferences.SharedPreferencesService
import com.example.dragonballappavanzado.di.annotations.LocalQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @LocalQualifier
    fun provideSharedPreferencesManager(context: Context): SharedPreferencesService = SharedPreferencesService(context)

    @Provides
    fun providesHeroDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, "characters-database"
        ).build()
    }

    @Provides
    fun providesHeroDao(db: CharacterDatabase): CharacterDAO {
        return db.characterDao()
    }

    @Provides
    fun providesLocalDataSourceInterface(localDataSource: LocalDataSource): LocalDataSourceInterface {
        return localDataSource
    }
}