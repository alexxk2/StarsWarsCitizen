package com.example.starswarscitizen.data.network

import com.example.starswarscitizen.data.network.dto.PeopleSearchResponse
import com.example.starswarscitizen.data.network.dto.PlanetsSearchResponse
import com.example.starswarscitizen.data.network.dto.StarshipsSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApiService {

    @GET("/api/starships")
    suspend fun getStarships(
        @Query("search")searchInput: String
    ): Response<StarshipsSearchResponse>

    @GET("/api/people")
    suspend fun getPeople(
        @Query("search")searchInput: String
    ): Response<PeopleSearchResponse>

    @GET("/api/planets")
    suspend fun getPlanets(
        @Query("search")searchInput: String
    ): Response<PlanetsSearchResponse>
}