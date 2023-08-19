package com.example.starswarscitizen.di

import com.example.starswarscitizen.domain.search.GetSearchResultUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetSearchResultUseCase> { GetSearchResultUseCase(searchRepository = get()) }

}