package com.example.starswarscitizen.di

import com.example.starswarscitizen.domain.favourite.AddToFavouritesUseCase
import com.example.starswarscitizen.domain.favourite.DeleteAllFavouritesUseCase
import com.example.starswarscitizen.domain.favourite.DeleteFromFavouritesUseCase
import com.example.starswarscitizen.domain.favourite.GetAllFavouritesUseCase
import com.example.starswarscitizen.domain.favourite.GetFavouritesNamesUseCase
import com.example.starswarscitizen.domain.search.GetSearchResultUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetSearchResultUseCase> { GetSearchResultUseCase(searchRepository = get(), favouritesRepository = get()) }

    factory<AddToFavouritesUseCase> { AddToFavouritesUseCase(favouritesRepository = get()) }

    factory<DeleteFromFavouritesUseCase> { DeleteFromFavouritesUseCase(favouritesRepository = get()) }

    factory<DeleteAllFavouritesUseCase> { DeleteAllFavouritesUseCase(favouritesRepository = get()) }

    factory<GetAllFavouritesUseCase> { GetAllFavouritesUseCase(favouritesRepository = get()) }

    factory<GetFavouritesNamesUseCase> { GetFavouritesNamesUseCase(favouritesRepository = get()) }

}