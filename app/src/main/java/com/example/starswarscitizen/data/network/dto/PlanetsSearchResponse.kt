package com.example.starswarscitizen.data.network.dto

data class PlanetsSearchResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PlanetDto>
)