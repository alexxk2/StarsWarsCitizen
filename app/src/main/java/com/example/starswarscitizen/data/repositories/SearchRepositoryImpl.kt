package com.example.starswarscitizen.data.repositories

import com.example.starswarscitizen.data.converters.SearchConverter
import com.example.starswarscitizen.data.network.NetworkClient
import com.example.starswarscitizen.domain.models.Human
import com.example.starswarscitizen.domain.models.Planet
import com.example.starswarscitizen.domain.models.Starship
import com.example.starswarscitizen.domain.repositories.SearchRepository

class SearchRepositoryImpl(
    private val networkClient: NetworkClient,
    private val converter: SearchConverter
) : SearchRepository {


    override suspend fun getPeopleSearchResult(searchInput: String): List<Human> {
        val resultFromData = networkClient.getPeopleSearchResult(searchInput)
        return resultFromData.map { humanDto ->
            converter.mapHumanToDomain(humanDto)
        }
    }

    override suspend fun getPlanetsSearchResult(searchInput: String): List<Planet> {
        val resultFromData = networkClient.getPlanetsSearchResult(searchInput)
        return resultFromData.map { planetDto ->
            converter.mapPlanetToDomain(planetDto)
        }
    }

    override suspend fun getStarshipsSearchResult(searchInput: String): List<Starship> {
        val resultFromData = networkClient.getStarshipsSearchResult(searchInput)
        return resultFromData.map { starshipDto ->
            converter.mapStarshipToDomain(starshipDto)
        }
    }
}