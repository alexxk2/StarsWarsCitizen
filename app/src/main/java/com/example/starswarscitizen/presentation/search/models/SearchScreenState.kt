package com.example.starswarscitizen.presentation.search.models

sealed interface SearchScreenState{
    object Content: SearchScreenState
    object Empty: SearchScreenState
    object Error: SearchScreenState
    object Loading: SearchScreenState
    object Intro: SearchScreenState
}