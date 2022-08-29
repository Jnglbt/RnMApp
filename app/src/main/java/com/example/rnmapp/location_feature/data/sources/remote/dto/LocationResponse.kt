package com.example.rnmapp.location_feature.data.sources.remote.dto

data class LocationResponse(
    val info: Info,
    val results: List<LocationDto>
)