package com.example.rnmapp.episodes_feature.data.sources.remote.dto

import com.example.rnmapp.episodes_feature.domain.models.Episode

data class EpisodeDto(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)

fun EpisodeDto.toEpisode(): Episode {
    return Episode(
        air_date = air_date,
        characters = characters,
        created = created,
        episode = episode,
        id = id,
        name = name,
        url = url
    )
}