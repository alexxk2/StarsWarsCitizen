package com.example.starswarscitizen.domain.favourite

import com.example.starswarscitizen.domain.repositories.FavouritesRepository

class GetFavouritesNamesUseCase(private val favouritesRepository: FavouritesRepository) {

    suspend fun execute(): List<String> = favouritesRepository.getFavouritesNames()
}