package com.example.starswarscitizen.domain.favourite

import com.example.starswarscitizen.domain.repositories.FavouritesRepository

class DeleteAllFavouritesUseCase(private val favouritesRepository: FavouritesRepository) {

    suspend fun execute() = favouritesRepository.deleteAllFavourites()
}