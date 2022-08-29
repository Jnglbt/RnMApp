package com.example.rnmapp.location_feature.data.sources.remote.dto

import com.example.rnmapp.location_feature.domain.models.Location

data class LocationDto(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)

fun LocationDto.toLocation(): Location {
    return Location(
        created = created,
        dimension = dimension,
        id = id,
        name = name,
        residents = residents,
        type = type,
        url = url
    )
}