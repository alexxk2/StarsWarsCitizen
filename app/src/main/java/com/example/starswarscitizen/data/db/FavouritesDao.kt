package com.example.starswarscitizen.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.starswarscitizen.data.db.dto.StarWarsItemDto


@Dao
interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewItem(starWarsItemDto: StarWarsItemDto)

    @Delete
    suspend fun deleteItem(starWarsItemDto: StarWarsItemDto)

    @Query("SELECT * FROM favourites ORDER BY name ASC")
    suspend fun getAllItems(): List<StarWarsItemDto>

    @Query("SELECT name FROM favourites")
    suspend fun getItemsNames(): List<String>

    @Query("DELETE FROM favourites")
    suspend fun deleteAllItems()
}