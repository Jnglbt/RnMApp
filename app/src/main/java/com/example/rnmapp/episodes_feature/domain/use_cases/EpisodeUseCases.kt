package com.example.rnmapp.episodes_feature.domain.use_cases

import com.example.rnmapp.episodes_feature.domain.use_cases.get_episodes.GetEpisodesUseCase
import com.example.rnmapp.episodes_feature.domain.use_cases.get_episode.GetEpisodeUseCase
import com.example.rnmapp.episodes_feature.domain.use_cases.get_episode_by_code.GetEpisodeByCodeUseCase
import com.example.rnmapp.episodes_feature.domain.use_cases.get_next_page.GetNextEpisodePageUseCase
import com.example.rnmapp.episodes_feature.domain.use_cases.get_previous_page.GetPreviousEpisodePageUseCase


data class EpisodeUseCases(
    val getEpisodeUseCase: GetEpisodeUseCase,
    val getEpisodesUseCase: GetEpisodesUseCase,
    val getNextEpisodePageUseCase: GetNextEpisodePageUseCase,
    val getPreviousEpisodePageUseCase: GetPreviousEpisodePageUseCase,
    val getEpisodeByCodeUseCase: GetEpisodeByCodeUseCase
)
