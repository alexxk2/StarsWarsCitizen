package com.example.starswarscitizen.di

import com.example.starswarscitizen.data.converters.SearchConverter
import com.example.starswarscitizen.data.network.NetworkClient
import com.example.starswarscitizen.data.network.impl.NetworkClientImpl
import com.example.starswarscitizen.data.repositories.SearchRepositoryImpl
import com.example.starswarscitizen.domain.repositories.SearchRepository
import org.koin.dsl.module

val dataModule = module {

    single<SearchConverter> { SearchConverter() }

    single<NetworkClient> { NetworkClientImpl() }

    single<SearchRepository> { SearchRepositoryImpl(networkClient = get(), converter = get()) }
}