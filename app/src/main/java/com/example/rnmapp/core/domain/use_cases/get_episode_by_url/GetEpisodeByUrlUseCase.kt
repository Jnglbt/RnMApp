package com.example.rnmapp.core.domain.use_cases.get_episode_by_url

import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.toEpisode
import com.example.rnmapp.episodes_feature.domain.models.Episode
import com.example.rnmapp.episodes_feature.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEpisodeByUrlUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(url: String): Flow<Resource<Episode>> = flow {
        try {
            emit(Resource.Loading<Episode>())
            val episode = repository.getEpisodeByUrl(url).toEpisode()
            emit(Resource.Success<Episode>(episode))
        } catch(e: HttpException) {
            emit(Resource.Error<Episode>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<Episode>("Couldn't reach server. Check your internet connection."))
        }
    }
}