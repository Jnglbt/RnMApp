package com.example.rnmapp.characters_feature.domain.models

import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.Location
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.Origin

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
