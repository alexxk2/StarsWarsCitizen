package com.example.starswarscitizen.domain.favourite

import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.domain.repositories.FavouritesRepository
import javax.inject.Inject

class AddToFavouritesUseCase @Inject constructor(private val favouritesRepository: FavouritesRepository) {

    suspend fun execute(starWarsItem: StarWarsItem) = favouritesRepository.addToFavourites(starWarsItem)
}