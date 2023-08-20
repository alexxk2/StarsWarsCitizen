package com.example.starswarscitizen.data.network

import com.example.starswarscitizen.data.network.dto.HumanDto
import com.example.starswarscitizen.data.network.dto.PlanetDto
import com.example.starswarscitizen.data.network.dto.StarshipDto

interface NetworkClient {

    suspend fun getPeopleSearchResult(searchInput: String): List<HumanDto>

    suspend fun getPlanetsSearchResult(searchInput: String): List<PlanetDto>

    suspend fun getStarshipsSearchResult(searchInput: String): List<StarshipDto>
}
