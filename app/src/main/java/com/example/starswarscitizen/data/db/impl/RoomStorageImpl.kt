package com.example.starswarscitizen.data.db.impl

import com.example.starswarscitizen.data.db.FavouritesDao
import com.example.starswarscitizen.data.db.RoomStorage
import com.example.starswarscitizen.data.db.dto.StarWarsItemDto
import javax.inject.Inject

class RoomStorageImpl @Inject constructor(private val favouritesDao: FavouritesDao) : RoomStorage {

    override suspend fun addToFavourites(starWarsItemDto: StarWarsItemDto) {
        favouritesDao.addNewItem(starWarsItemDto)
    }

    override suspend fun deleteFromFavourites(starWarsItemDto: StarWarsItemDto) {
        favouritesDao.deleteItem(starWarsItemDto)
    }

    override suspend fun deleteAllFavourites() = favouritesDao.deleteAllItems()


    override suspend fun getAllFavourites(): List<StarWarsItemDto> = favouritesDao.getAllItems()

    override suspend fun getFavouritesNames(): List<String> = favouritesDao.getItemsNames()
}