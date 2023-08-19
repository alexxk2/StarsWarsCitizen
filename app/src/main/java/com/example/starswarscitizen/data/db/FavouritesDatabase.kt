package com.example.starswarscitizen.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.starswarscitizen.data.db.dto.StarWarsItemDto

@Database(entities = [StarWarsItemDto::class], version = 1, exportSchema = false)
abstract class FavouritesDatabase : RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao

    companion object {

        private var INSTANCE: FavouritesDatabase? = null

        fun getDataBase(context: Context): FavouritesDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    FavouritesDatabase::class.java,
                    "favourites_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}