package com.example.starswarscitizen.data.repositories

import com.example.starswarscitizen.data.converters.FavouritesConverter
import com.example.starswarscitizen.data.db.RoomStorage
import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.domain.repositories.FavouritesRepository
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val roomStorage: RoomStorage,
    private val converter: FavouritesConverter
) : FavouritesRepository {


    override suspend fun addToFavourites(starWarsItem: StarWarsItem) {
        val mappedItem = converter.mapFavouriteToData(starWarsItem)
        roomStorage.addToFavourites(starWarsItemDto = mappedItem)
    }

    override suspend fun deleteFromFavourites(starWarsItem: StarWarsItem) {
        val mappedItem = converter.mapFavouriteToData(starWarsItem)
        roomStorage.deleteFromFavourites(starWarsItemDto = mappedItem)
    }

    override suspend fun deleteAllFavourites() {
        roomStorage.deleteAllFavourites()
    }

    override suspend fun getAllFavourites(): List<StarWarsItem> {
        val resultFromData = roomStorage.getAllFavourites()
        return resultFromData.map { starWarsItemDto ->
            converter.mapFavouriteToDomain(starWarsItemDto = starWarsItemDto)
        }
    }

    override suspend fun getFavouritesNames(): List<String> = roomStorage.getFavouritesNames()
}