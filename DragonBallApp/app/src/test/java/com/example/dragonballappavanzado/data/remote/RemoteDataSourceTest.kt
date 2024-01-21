package com.example.dragonballappavanzado.data.remote

import com.example.dragonballappavanzado.utils.MockWebDispatcher
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteDataSourceTest {

    // Dependencies
    private lateinit var api: DragonBallApi

    @Before
    fun setUp(){
        val mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockWebDispatcher()
        mockWebServer.start()
        val loggerInterceptor =  HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggerInterceptor).build()

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        api = Retrofit.Builder().client(okHttpClient).baseUrl(mockWebServer.url("/")).addConverterFactory(
            MoshiConverterFactory.create(moshi)).build().create(DragonBallApi::class.java)
    }

    @Test
    fun `WHEN getHeroes THEN success list`() = runTest{
        // GIVEN
        val remoteDataSource = RemoteDataSource(api)

        // WHEN
        val heroList = remoteDataSource.getHeroes()

        // THEN
        Assert.assertEquals(true, heroList.isNotEmpty())
    }

    @After
    fun tearDown(){

    }
}