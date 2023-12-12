package com.example.starswarscitizen.presentation.search.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starswarscitizen.domain.favourite.AddToFavouritesUseCase
import com.example.starswarscitizen.domain.favourite.DeleteFromFavouritesUseCase
import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.domain.search.GetSearchResultUseCase
import com.example.starswarscitizen.presentation.search.models.SearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val deleteFromFavouritesUseCase: DeleteFromFavouritesUseCase,
    private val addToFavouritesUseCase: AddToFavouritesUseCase
) : ViewModel() {

    private var searchJob: Job? = null

    private val _screenState = MutableLiveData<SearchScreenState>(SearchScreenState.Intro)
    val screenState: LiveData<SearchScreenState> = _screenState

    private val _searchResults = MutableLiveData<List<StarWarsItem>>()
    val searchResults: LiveData<List<StarWarsItem>> = _searchResults


    fun startSearch(searchInput: String) {
        _screenState.value = SearchScreenState.Loading

        viewModelScope.launch {
            try {
                val searchingResult = getSearchResultUseCase.execute(searchInput)
                if (searchingResult.isNotEmpty()) {
                    _searchResults.value = searchingResult
                    _screenState.value = SearchScreenState.Content
                } else {
                    _screenState.value = SearchScreenState.Empty
                }
            } catch (e: Exception) {
                _screenState.value = SearchScreenState.Error
            }

        }

    }

    fun addToFavourite(starWarsItem: StarWarsItem) {
        viewModelScope.launch {
            addToFavouritesUseCase.execute(starWarsItem)

            _searchResults.value = _searchResults.value?.map {
                if (it.name == starWarsItem.name) {
                    it.copy(isFavourite = true)
                } else it
            }

        }
    }

    fun removeFromFavourite(starWarsItem: StarWarsItem) {
        viewModelScope.launch {
            deleteFromFavouritesUseCase.execute(starWarsItem)

            _searchResults.value = _searchResults.value?.map {
                if (it.name == starWarsItem.name) {
                    it.copy(isFavourite = false)
                } else it
            }
        }
    }

    fun setIntro() {
        _screenState.value = SearchScreenState.Intro
        _searchResults.value = emptyList()
    }


    fun searchDebounce(s: CharSequence?) {
        if (!s.isNullOrEmpty() && s.length >= 2) {

            _screenState.value = SearchScreenState.Loading

            val searchInput = s.toString()
            searchJob?.cancel()

            searchJob = viewModelScope.launch {
                delay(SEARCH_DEBOUNCE_DELAY)
                startSearch(searchInput)
            }
        }
    }

    companion object {
        const val SEARCH_DEBOUNCE_DELAY = 2000L
    }


}