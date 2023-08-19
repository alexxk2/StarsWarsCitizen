package com.example.starswarscitizen.presentation.favourite.models

sealed interface FavouritesScreenState{
    object Content: FavouritesScreenState
    object Empty: FavouritesScreenState
    object Error: FavouritesScreenState
    object Loading: FavouritesScreenState
}