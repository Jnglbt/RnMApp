package com.example.rnmapp.episodes_feature.domain.use_cases.get_episodes

import android.util.Log
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.toCharacter
import com.example.rnmapp.characters_feature.domain.models.Character
import com.example.rnmapp.characters_feature.domain.repository.CharacterRepository
import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeResponse
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.toEpisode
import com.example.rnmapp.episodes_feature.domain.models.Episode
import com.example.rnmapp.episodes_feature.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEpisodesUseCase
@Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(pageNumber: Int?): Flow<Resource<EpisodeResponse>> = flow {
        try {
            emit(Resource.Loading<EpisodeResponse>())
            val episodes = repository.getEpisodes(pageNumber)
            emit(Resource.Success<EpisodeResponse>(episodes))
        } catch (e: HttpException) {
            emit(
                Resource.Error<EpisodeResponse>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<EpisodeResponse>("Couldn't reach server. Check your internet connection."))
        }
    }
}