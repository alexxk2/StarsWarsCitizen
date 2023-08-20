package com.example.starswarscitizen.data.db.impl

import com.example.starswarscitizen.data.db.FavouritesDatabase
import com.example.starswarscitizen.data.db.RoomStorage
import com.example.starswarscitizen.data.db.dto.StarWarsItemDto

class RoomStorageImpl(private val favouritesDatabase: FavouritesDatabase) : RoomStorage {

    private val favouritesDao = favouritesDatabase.favouritesDao()

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