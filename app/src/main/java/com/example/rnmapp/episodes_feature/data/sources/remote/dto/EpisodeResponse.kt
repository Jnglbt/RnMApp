package com.example.rnmapp.episodes_feature.data.sources.remote.dto

data class EpisodeResponse(
    val info: Info,
    val results: List<EpisodeDto>
)