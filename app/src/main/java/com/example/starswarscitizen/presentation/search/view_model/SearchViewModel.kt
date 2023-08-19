package com.example.starswarscitizen.presentation.search.view_model

import androidx.lifecycle.ViewModel
import com.example.starswarscitizen.domain.search.GetSearchResultUseCase

class SearchViewModel(
    private val getSearchResultUseCase: GetSearchResultUseCase
): ViewModel() {
}