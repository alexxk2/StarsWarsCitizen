package com.example.starswarscitizen.data.network.impl

import com.example.starswarscitizen.data.network.NetworkClient
import com.example.starswarscitizen.data.network.StarWarsApiService
import com.example.starswarscitizen.data.network.dto.HumanDto
import com.example.starswarscitizen.data.network.dto.PlanetDto
import com.example.starswarscitizen.data.network.dto.StarshipDto
import javax.inject.Inject

class NetworkClientImpl @Inject constructor (private val retrofitService: StarWarsApiService) : NetworkClient {

    override suspend fun getPeopleSearchResult(searchInput: String): List<HumanDto> {

        val response = retrofitService.getPeople(searchInput)

        return if (response.code() == 200) {
            response.body()?.results ?: emptyList()
        } else emptyList()

    }

    override suspend fun getPlanetsSearchResult(searchInput: String): List<PlanetDto> {

        val response = retrofitService.getPlanets(searchInput)

        return if (response.code() == 200) {
            response.body()?.results ?: emptyList()
        } else emptyList()
    }

    override suspend fun getStarshipsSearchResult(searchInput: String): List<StarshipDto> {

        val response = retrofitService.getStarships(searchInput)

        return if (response.code() == 200) {
            response.body()?.results ?: emptyList()
        } else emptyList()
    }
}