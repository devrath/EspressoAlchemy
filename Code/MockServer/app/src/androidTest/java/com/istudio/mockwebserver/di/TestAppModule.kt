package com.istudio.mockwebserver.di

import com.istudio.mockwebserver.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    private const val BASE_URL = "https://raw.githubusercontent.com/devrath/Sample-Data/master/Android-CleanArchitecture-Kotlin/"

    // Construct the API class
    @Singleton
    @Provides
    fun providePokeApi(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)
    }



}