package com.example.rnmapp.characters_feature.presentation.character_detail

import com.example.rnmapp.characters_feature.domain.models.Character
import com.example.rnmapp.episodes_feature.domain.models.Episode

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val episodes: MutableList<Episode?> = mutableListOf(),
    val error: String = ""
)
