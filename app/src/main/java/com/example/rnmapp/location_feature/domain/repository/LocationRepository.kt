package com.example.rnmapp.location_feature.domain.repository

import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterDto
import com.example.rnmapp.location_feature.data.sources.remote.dto.LocationDto
import com.example.rnmapp.location_feature.data.sources.remote.dto.LocationResponse

interface LocationRepository {

    suspend fun getLocations(pageNumber: Int?): LocationResponse

    suspend fun  getLocationById(locationId: String): LocationDto

    suspend fun  getNextLocationPage(url: String): LocationResponse

    suspend fun getPreviousLocationPage(url: String) : LocationResponse

    suspend fun getLocationsByName(name: String): LocationResponse

    suspend fun getLocationByUrl(url: String) : LocationDto
}