package com.example.starswarscitizen.domain.favourite

import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.domain.repositories.FavouritesRepository

class GetAllFavouritesUseCase(private val favouritesRepository: FavouritesRepository) {

    suspend fun execute(): List<StarWarsItem> = favouritesRepository.getAllFavourites()
}