package com.example.starswarscitizen.data.converters

import com.example.starswarscitizen.data.db.dto.StarWarsItemDto
import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.domain.models.StarWarsObject

class FavouritesConverter {

    fun mapFavouriteToData(starWarsItem: StarWarsItem): StarWarsItemDto {

        val isPlanet = starWarsItem.type is StarWarsObject.Planet
        val isPeople = starWarsItem.type is StarWarsObject.People
        val isStarship = starWarsItem.type is StarWarsObject.Starship

        with(starWarsItem) {
            return StarWarsItemDto(
                name = name,
                infoField1 = infoField1,
                infoField2 = infoField2,
                infoField3 = infoField3,
                isPlanet = isPlanet,
                isPeople = isPeople,
                isStarship = isStarship,
                isFavourite = true
            )
        }

    }

    fun mapFavouriteToDomain(starWarsItemDto: StarWarsItemDto): StarWarsItem {

        val type = when {
            starWarsItemDto.isPlanet -> StarWarsObject.Planet
            starWarsItemDto.isPeople -> StarWarsObject.People
            else -> StarWarsObject.Starship
        }

        with(starWarsItemDto) {
            return StarWarsItem(
                name = name,
                infoField1 = infoField1,
                infoField2 = infoField2,
                infoField3 = infoField3,
                type =type,
                isFavourite = isFavourite
            )
        }

    }
}