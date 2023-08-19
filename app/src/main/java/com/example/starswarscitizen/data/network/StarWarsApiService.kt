package com.example.starswarscitizen.data.network

import com.example.starswarscitizen.data.network.dto.PeopleSearchResponse
import com.example.starswarscitizen.data.network.dto.PlanetsSearchResponse
import com.example.starswarscitizen.data.network.dto.StarshipsSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApiService {

    @GET("/starships")
    suspend fun getStarships(
        @Query("search")searchInput: String
    ): Response<StarshipsSearchResponse>

    @GET("/people")
    suspend fun getPeople(
        @Query("search")searchInput: String
    ): Response<PeopleSearchResponse>

    @GET("/planets")
    suspend fun getPlanets(
        @Query("search")searchInput: String
    ): Response<PlanetsSearchResponse>
}