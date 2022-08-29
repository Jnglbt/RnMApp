package com.example.rnmapp.episodes_feature.domain.use_cases.get_episode_by_code

import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeResponse
import com.example.rnmapp.episodes_feature.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEpisodeByCodeUseCase @Inject constructor (
    private val repository: EpisodeRepository
) {
    operator fun invoke(episode: String): Flow<Resource<EpisodeResponse>> = flow {
        try {
            emit(Resource.Loading<EpisodeResponse>())
            val episodes = repository.getEpisodeByCode(episode)
            emit(Resource.Success<EpisodeResponse>(episodes))
        } catch(e: HttpException) {
            emit(Resource.Error<EpisodeResponse>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<EpisodeResponse>("Couldn't reach server. Check your internet connection."))
        }
    }
}