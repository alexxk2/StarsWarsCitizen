package com.example.starswarscitizen.data.network.impl

import com.example.starswarscitizen.data.network.NetworkClient
import com.example.starswarscitizen.data.network.StarWarsApiService
import com.example.starswarscitizen.data.network.dto.HumanDto
import com.example.starswarscitizen.data.network.dto.PlanetDto
import com.example.starswarscitizen.data.network.dto.StarshipDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClientImpl : NetworkClient {

    private val baseUrl = "https://swapi.dev"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: StarWarsApiService by lazy {
        retrofit.create(StarWarsApiService::class.java)
    }

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