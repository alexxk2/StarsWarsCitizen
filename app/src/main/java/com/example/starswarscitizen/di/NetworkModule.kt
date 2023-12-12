package com.example.starswarscitizen.di

import com.example.starswarscitizen.data.network.NetworkClient
import com.example.starswarscitizen.data.network.StarWarsApiService
import com.example.starswarscitizen.data.network.impl.NetworkClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        val baseUrl = "https://swapi.dev"
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideStarWarsApiService(retrofit: Retrofit): StarWarsApiService {
        return retrofit.create(StarWarsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkClient(retrofitService: StarWarsApiService): NetworkClient {
        return NetworkClientImpl(retrofitService)
    }
}