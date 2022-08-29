package com.example.rnmapp.episodes_feature.presentation.episode_detail

import com.example.rnmapp.characters_feature.domain.models.Character
import com.example.rnmapp.episodes_feature.domain.models.Episode

data class EpisodeDetailState(
    val isLoading: Boolean = false,
    val episode: Episode? = null,
    val characters: MutableList<Character?> = mutableListOf(),
    val error: String = ""
)
