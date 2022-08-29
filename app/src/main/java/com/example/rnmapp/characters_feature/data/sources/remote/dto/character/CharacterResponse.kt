package com.example.rnmapp.characters_feature.data.sources.remote.dto.character

data class CharacterResponse(
    val info: Info,
    val results: List<CharacterDto>
)