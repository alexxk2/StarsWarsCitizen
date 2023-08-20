package com.example.starswarscitizen.domain.repositories

import com.example.starswarscitizen.domain.models.Human
import com.example.starswarscitizen.domain.models.Planet
import com.example.starswarscitizen.domain.models.Starship

interface SearchRepository {
    suspend fun getPeopleSearchResult(searchInput: String): List<Human>

    suspend fun getPlanetsSearchResult(searchInput: String): List<Planet>

    suspend fun getStarshipsSearchResult(searchInput: String): List<Starship>
}