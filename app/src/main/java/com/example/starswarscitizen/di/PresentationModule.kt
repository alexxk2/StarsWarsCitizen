package com.example.starswarscitizen.di

import com.example.starswarscitizen.presentation.search.view_model.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<SearchViewModel> { SearchViewModel(getSearchResultUseCase = get()) }
}