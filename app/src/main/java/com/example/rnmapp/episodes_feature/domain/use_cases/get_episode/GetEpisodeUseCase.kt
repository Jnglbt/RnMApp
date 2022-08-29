package com.example.rnmapp.episodes_feature.domain.use_cases.get_episode

import android.util.Log
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

class GetEpisodeUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(characterId: String): Flow<Resource<Episode>> = flow {
        try {
            emit(Resource.Loading<Episode>())
            val episode = repository.getEpisodeById(characterId).toEpisode()
            Log.d("Get Character", episode.id.toString())
            emit(Resource.Success<Episode>(episode))
        } catch (e: HttpException) {
            emit(Resource.Error<Episode>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Episode>("Couldn't reach server. Check your internet connection."))
        }
    }
}