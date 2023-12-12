package com.example.starswarscitizen.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starswarscitizen.data.db.dto.StarWarsItemDto

@Database(entities = [StarWarsItemDto::class], version = 1, exportSchema = false)
abstract class FavouritesDatabase : RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao
}