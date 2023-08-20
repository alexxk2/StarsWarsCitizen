package com.example.starswarscitizen.domain.models

sealed interface StarWarsObject{
    object People: StarWarsObject
    object Starship: StarWarsObject
    object Planet: StarWarsObject
}