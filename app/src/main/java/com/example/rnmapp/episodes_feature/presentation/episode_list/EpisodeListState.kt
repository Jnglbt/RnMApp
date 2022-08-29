package com.example.rnmapp.episodes_feature.presentation.episode_list

import com.example.rnmapp.episodes_feature.domain.models.Episode

data class EpisodeListState(
    val isLoading: Boolean = false,
    val episodes: List<Episode> = emptyList(),
    val next: String? = null,
    val previous: String? = null,
    val text: String = "",
    val hint: String = "Enter episode",
    val isHintVisible: Boolean = true,
    val isNavigationVisible: Boolean = true,
    val error: String = ""
)
