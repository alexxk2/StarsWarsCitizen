package com.example.starswarscitizen.data.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starswarscitizen.domain.models.StarWarsObject

@Entity(tableName = "favourites")
data class StarWarsItemDto(
    @PrimaryKey(autoGenerate = false)val name: String,
    @ColumnInfo(name = "info_field_1") val infoField1: String,
    @ColumnInfo(name = "info_field_2")val infoField2: String,
    @ColumnInfo(name = "info_field_3")val infoField3: String,
    @ColumnInfo(name = "is_planet")val isPlanet: Boolean,
    @ColumnInfo(name = "is_people")val isPeople: Boolean,
    @ColumnInfo(name = "is_starship")val isStarship: Boolean,
    @ColumnInfo(name = "is_favourite")val isFavourite: Boolean,
)
