package com.example.dragonballappavanzado.di

import android.content.Context
import android.content.SharedPreferences
import com.example.dragonballappavanzado.data.local.sharedPreferences.SharedPreferencesService
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
    fun provideSharedPreferencesManager(context: Context): SharedPreferencesService = SharedPreferencesService(context)

}