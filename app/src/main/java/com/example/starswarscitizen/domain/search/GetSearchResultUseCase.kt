package com.example.starswarscitizen.domain.search

import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.domain.models.StarWarsObject
import com.example.starswarscitizen.domain.repositories.SearchRepository

class GetSearchResultUseCase(private val searchRepository: SearchRepository) {

    //передать сюда поисковый запрос и собрать лист результатов из готовых листов результатов поиска по 3м категориям

    suspend fun execute(searchInput: String): List<StarWarsItem> {


        val listFromPlanet = searchRepository.getPlanetsSearchResult(searchInput).map { planet ->
            StarWarsItem(
                name = planet.name,
                infoField1 = planet.diameter,
                infoField2 = planet.population,
                infoField3 = "",
                type = StarWarsObject.Planet
            )
        }

        val listFromPeople = searchRepository.getPeopleSearchResult(searchInput).map { human ->
            StarWarsItem(
                name = human.name,
                infoField1 = human.gender,
                infoField2 = human.starshipsNumber.toString(),
                infoField3 = "",
                type = StarWarsObject.People
            )
        }
        val listFromStarships =
            searchRepository.getStarshipsSearchResult(searchInput).map { starship ->
                StarWarsItem(
                    name = starship.name,
                    infoField1 = starship.model,
                    infoField2 = starship.manufacturer,
                    infoField3 = starship.passengers,
                    type = StarWarsObject.Starship
                )
            }


        return if (listFromPeople.isNotEmpty() || listFromPlanet.isNotEmpty() || listFromStarships.isNotEmpty()) {
            val resultList = mutableListOf<StarWarsItem>()
            resultList.addAll(listFromPeople)
            resultList.addAll(listFromPlanet)
            resultList.addAll(listFromStarships)

            resultList.sortedBy { it.name }

        } else return emptyList()

    }

}