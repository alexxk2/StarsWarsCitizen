package com.example.starswarscitizen.data.converters

import com.example.starswarscitizen.data.network.dto.HumanDto
import com.example.starswarscitizen.data.network.dto.PlanetDto
import com.example.starswarscitizen.data.network.dto.StarshipDto
import com.example.starswarscitizen.domain.models.Human
import com.example.starswarscitizen.domain.models.Planet
import com.example.starswarscitizen.domain.models.Starship
import javax.inject.Inject

class SearchConverter @Inject constructor() {

    fun mapHumanToDomain(humanDto: HumanDto): Human {
        with(humanDto) {
            return Human(
                name = name,
                gender = gender,
                starshipsNumber = starships.size
            )
        }
    }

    fun mapPlanetToDomain(planetDto: PlanetDto): Planet {
        with(planetDto) {
            return Planet(
                name = name,
                diameter = diameter,
                population = population
            )
        }
    }

    fun mapStarshipToDomain(starshipDto: StarshipDto): Starship {
        with(starshipDto) {
            return Starship(
                name = name,
                model = model,
                manufacturer = manufacturer,
                passengers = passengers
            )
        }
    }
}