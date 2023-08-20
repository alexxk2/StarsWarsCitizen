package com.example.starswarscitizen.domain.models

data class StarWarsItem(
    val name: String,
    val infoField1: String,
    val infoField2: String,
    val infoField3: String,
    val type: StarWarsObject,
    val isFavourite: Boolean = false
)
