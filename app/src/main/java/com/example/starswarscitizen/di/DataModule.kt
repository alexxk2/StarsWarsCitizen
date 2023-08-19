package com.example.starswarscitizen.di

import com.example.starswarscitizen.data.converters.FavouritesConverter
import com.example.starswarscitizen.data.converters.SearchConverter
import com.example.starswarscitizen.data.db.FavouritesDatabase
import com.example.starswarscitizen.data.db.RoomStorage
import com.example.starswarscitizen.data.db.impl.RoomStorageImpl
import com.example.starswarscitizen.data.network.NetworkClient
import com.example.starswarscitizen.data.network.impl.NetworkClientImpl
import com.example.starswarscitizen.data.repositories.FavouritesRepositoryImpl
import com.example.starswarscitizen.data.repositories.SearchRepositoryImpl
import com.example.starswarscitizen.domain.repositories.FavouritesRepository
import com.example.starswarscitizen.domain.repositories.SearchRepository
import org.koin.dsl.module

val dataModule = module {

    single<SearchConverter> { SearchConverter() }

    single<NetworkClient> { NetworkClientImpl() }

    single<SearchRepository> { SearchRepositoryImpl(networkClient = get(), converter = get()) }



    single<FavouritesConverter> { FavouritesConverter() }

    single<FavouritesDatabase> { FavouritesDatabase.getDataBase(context = get()) }

    single<RoomStorage> {RoomStorageImpl(favouritesDatabase = get())  }

    single<FavouritesRepository> {FavouritesRepositoryImpl(roomStorage = get(), converter = get())  }
}