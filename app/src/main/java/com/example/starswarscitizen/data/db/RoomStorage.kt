package com.example.starswarscitizen.data.db

import com.example.starswarscitizen.data.db.dto.StarWarsItemDto
import com.example.starswarscitizen.domain.models.StarWarsItem

interface RoomStorage {

    suspend fun addToFavourites(starWarsItemDto: StarWarsItemDto)

    suspend fun deleteFromFavourites(starWarsItemDto: StarWarsItemDto)

    suspend fun deleteAllFavourites()

    suspend fun getAllFavourites(): List<StarWarsItemDto>

    suspend fun getFavouritesNames(): List<String>
}