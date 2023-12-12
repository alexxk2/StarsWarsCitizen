package com.example.starswarscitizen.domain.favourite

import com.example.starswarscitizen.domain.repositories.FavouritesRepository
import javax.inject.Inject

class DeleteAllFavouritesUseCase @Inject constructor(private val favouritesRepository: FavouritesRepository) {

    suspend fun execute() = favouritesRepository.deleteAllFavourites()
}