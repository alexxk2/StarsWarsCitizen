package com.example.starswarscitizen.presentation.search.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.domain.search.GetSearchResultUseCase
import com.example.starswarscitizen.presentation.search.models.ScreenState
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSearchResultUseCase: GetSearchResultUseCase
) : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>(ScreenState.Intro)
    val screenState: LiveData<ScreenState> = _screenState

    private val _searchResults = MutableLiveData<List<StarWarsItem>>()
    val searchResults: LiveData<List<StarWarsItem>> = _searchResults


    fun startSearch(searchInput: String) {
        _screenState.value = ScreenState.Loading

        viewModelScope.launch {
            try {
                val searchingResult = getSearchResultUseCase.execute(searchInput)
                if (searchingResult.isNotEmpty()) {
                    _searchResults.value = searchingResult
                    _screenState.value = ScreenState.Content
                } else {
                    _screenState.value = ScreenState.Empty
                }
            } catch (e: Exception) {
                _screenState.value = ScreenState.Error
            }

        }

    }

}