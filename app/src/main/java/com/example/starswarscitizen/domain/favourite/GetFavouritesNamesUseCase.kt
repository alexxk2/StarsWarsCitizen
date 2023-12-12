package com.example.starswarscitizen.domain.favourite

import com.example.starswarscitizen.domain.repositories.FavouritesRepository
import javax.inject.Inject

class GetFavouritesNamesUseCase @Inject constructor(private val favouritesRepository: FavouritesRepository) {

    suspend fun execute(): List<String> = favouritesRepository.getFavouritesNames()
}