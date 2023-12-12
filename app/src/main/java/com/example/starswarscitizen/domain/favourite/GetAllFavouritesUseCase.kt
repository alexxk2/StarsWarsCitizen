package com.example.starswarscitizen.domain.favourite

import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.domain.repositories.FavouritesRepository
import javax.inject.Inject

class GetAllFavouritesUseCase @Inject constructor(private val favouritesRepository: FavouritesRepository) {

    suspend fun execute(): List<StarWarsItem> = favouritesRepository.getAllFavourites()
}