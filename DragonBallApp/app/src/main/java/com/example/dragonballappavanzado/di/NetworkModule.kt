package com.example.dragonballappavanzado.di

import android.content.Context
import com.example.dragonballappavanzado.data.local.sharedPreferences.SharedPreferencesService
import com.example.dragonballappavanzado.data.remote.DragonBallApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun provideSharedPreferencesManager(context: Context): SharedPreferencesService = SharedPreferencesService(context)

    @Provides
    fun providesOkHttpClient(sharedPreferencesService: SharedPreferencesService): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val originalRequest = chain.request()
            if (originalRequest.url.encodedPath.contains("login")) {
                val email = sharedPreferencesService.getEmail()
                val password = sharedPreferencesService.getPassword()
                val credentials = Credentials.basic(email, password)
                val newRequest =
                    originalRequest.newBuilder().addHeader("Authorization", credentials).build()
                chain.proceed(newRequest)
            } else if (originalRequest.url.encodedPath.contains("heros")) {
                val token = sharedPreferencesService.getToken()
                val newRequest =
                    originalRequest.newBuilder().addHeader("Authorization", "Bearer $token").build()
                chain.proceed(newRequest)
            } else {
                chain.proceed(originalRequest)
            }
        }.build()
    }

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl("https://dragonball.keepcoding.education/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Provides
    fun providesDragonBallApi(retrofit: Retrofit): DragonBallApi {
        return retrofit.create(DragonBallApi::class.java)
    }
}