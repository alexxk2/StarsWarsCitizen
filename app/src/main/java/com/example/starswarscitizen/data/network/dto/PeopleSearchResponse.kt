package com.example.starswarscitizen.data.network.dto

data class PeopleSearchResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<HumanDto>
)