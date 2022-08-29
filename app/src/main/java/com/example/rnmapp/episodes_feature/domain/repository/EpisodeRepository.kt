package com.example.rnmapp.episodes_feature.domain.repository

import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterDto
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterResponse
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeDto
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeResponse

interface EpisodeRepository {

    suspend fun getEpisodes(pageNumber: Int?): EpisodeResponse

    suspend fun  getEpisodeById(episodeId: String): EpisodeDto

    suspend fun  getNextEpisodePage(url: String): EpisodeResponse

    suspend fun getPreviousEpisodePage(url: String) : EpisodeResponse

    suspend fun getEpisodeByCode(episode: String): EpisodeResponse

    suspend fun getEpisodeByUrl(url: String) : EpisodeDto
}