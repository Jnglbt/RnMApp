package com.example.rnmapp.episodes_feature.data.repository

import com.example.rnmapp.api.RickAndMortyApi
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeDto
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeResponse
import com.example.rnmapp.episodes_feature.domain.repository.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : EpisodeRepository {

    override suspend fun getEpisodes(pageNumber: Int?): EpisodeResponse {
        return api.getEpisodes(pageNumber)
    }

    override suspend fun getEpisodeById(episodeId: String): EpisodeDto {
        return api.getEpisodeById(episodeId)
    }

    override suspend fun getNextEpisodePage(url: String): EpisodeResponse {
        return api.getNextEpisodePage(url)
    }

    override suspend fun getPreviousEpisodePage(url: String): EpisodeResponse {
        return api.getPreviousEpisodePage(url)
    }

    override suspend fun getEpisodeByCode(episode: String): EpisodeResponse {
        return api.getEpisodesByCode(episode)
    }

    override suspend fun getEpisodeByUrl(url: String): EpisodeDto {
        return api.getEpisodeByUrl(url)
    }
}