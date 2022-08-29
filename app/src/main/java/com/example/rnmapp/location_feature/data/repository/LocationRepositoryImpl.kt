package com.example.rnmapp.location_feature.data.repository

import com.example.rnmapp.api.RickAndMortyApi
import com.example.rnmapp.location_feature.data.sources.remote.dto.LocationDto
import com.example.rnmapp.location_feature.data.sources.remote.dto.LocationResponse
import com.example.rnmapp.location_feature.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : LocationRepository {

    override suspend fun getLocations(pageNumber: Int?): LocationResponse {
        return api.getLocations(pageNumber)
    }

    override suspend fun getLocationById(locationId: String): LocationDto {
        return api.getLocationById(locationId)
    }

    override suspend fun getNextLocationPage(url: String): LocationResponse {
        return api.getNextLocationPage(url)
    }

    override suspend fun getPreviousLocationPage(url: String): LocationResponse {
        return api.getPreviousLocationPage(url)
    }

    override suspend fun getLocationsByName(name: String): LocationResponse {
        return api.getLocationByName(name)
    }

    override suspend fun getLocationByUrl(url: String): LocationDto {
        return api.getLocationByUrl(url)
    }
}