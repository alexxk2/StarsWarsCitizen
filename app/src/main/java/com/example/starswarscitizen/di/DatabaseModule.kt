package com.example.starswarscitizen.di

import android.content.Context
import androidx.room.Room
import com.example.starswarscitizen.data.db.FavouritesDao
import com.example.starswarscitizen.data.db.FavouritesDatabase
import com.example.starswarscitizen.data.db.RoomStorage
import com.example.starswarscitizen.data.db.impl.RoomStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFavouritesDatabase(@ApplicationContext appContext: Context): FavouritesDatabase {
        return Room.databaseBuilder(
            appContext,
            FavouritesDatabase::class.java,
            "favourites_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFavouritesDao(favouritesDatabase: FavouritesDatabase): FavouritesDao {
        return favouritesDatabase.favouritesDao()
    }

    @Provides
    @Singleton
    fun provideRoomStorage(favouritesDao: FavouritesDao): RoomStorage {
        return RoomStorageImpl(favouritesDao)
    }
}