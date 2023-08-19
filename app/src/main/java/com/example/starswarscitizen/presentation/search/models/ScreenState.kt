package com.example.starswarscitizen.presentation.search.models

sealed interface ScreenState{
    object Content: ScreenState
    object Empty: ScreenState
    object Error: ScreenState
    object Loading: ScreenState
    object Intro: ScreenState
}