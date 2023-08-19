package com.example.starswarscitizen.domain.repositories

import com.example.starswarscitizen.domain.models.StarWarsItem

interface FavouritesRepository {

    suspend fun addToFavourites(starWarsItem: StarWarsItem)

    suspend fun deleteFromFavourites(starWarsItem: StarWarsItem)

    suspend fun deleteAllFavourites()

    suspend fun getAllFavourites(): List<StarWarsItem>

    suspend fun getFavouritesNames(): List<String>
}