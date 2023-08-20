package com.example.starswarscitizen.presentation.favourite.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starswarscitizen.domain.favourite.DeleteFromFavouritesUseCase
import com.example.starswarscitizen.domain.favourite.GetAllFavouritesUseCase
import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.presentation.favourite.models.FavouritesScreenState
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase,
    private val deleteFromFavouritesUseCase: DeleteFromFavouritesUseCase
): ViewModel() {


    private val _screenState = MutableLiveData<FavouritesScreenState>()
    val screenState: LiveData<FavouritesScreenState> = _screenState

    private val _listOfFavourite = MutableLiveData<List<StarWarsItem>>()
    val listOfFavourite: LiveData<List<StarWarsItem>> = _listOfFavourite


    fun getFavourites(){

        _screenState.value = FavouritesScreenState.Loading

        viewModelScope.launch {

            try {
                val resultFromData = getAllFavouritesUseCase.execute()
                if (resultFromData.isNotEmpty()){
                    _listOfFavourite.value = resultFromData
                    _screenState.value = FavouritesScreenState.Content
                }
                else{
                    _listOfFavourite.value = resultFromData
                    _screenState.value = FavouritesScreenState.Empty
                }

            }
            catch (e:Exception){
                _screenState.value = FavouritesScreenState.Error
            }
        }
    }


    fun removeFromFavourites(starWarsItem: StarWarsItem){
        viewModelScope.launch {
            deleteFromFavouritesUseCase.execute(starWarsItem)

            getFavourites()
        }

    }
}