package com.example.starswarscitizen.di

import com.example.starswarscitizen.data.repositories.FavouritesRepositoryImpl
import com.example.starswarscitizen.data.repositories.SearchRepositoryImpl
import com.example.starswarscitizen.domain.repositories.FavouritesRepository
import com.example.starswarscitizen.domain.repositories.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindFavouriteRepository(favouritesRepositoryImpl: FavouritesRepositoryImpl): FavouritesRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}