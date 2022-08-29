package com.example.rnmapp.episodes_feature.domain.use_cases.get_next_page

import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterResponse
import com.example.rnmapp.characters_feature.domain.repository.CharacterRepository
import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeResponse
import com.example.rnmapp.episodes_feature.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNextEpisodePageUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(url: String): Flow<Resource<EpisodeResponse>> = flow {
        try {
            emit(Resource.Loading<EpisodeResponse>())
            val next = repository.getNextEpisodePage(url)
            emit(Resource.Success<EpisodeResponse>(next))
        } catch(e: HttpException) {
            emit(Resource.Error<EpisodeResponse>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<EpisodeResponse>("Couldn't reach server. Check your internet connection."))
        }
    }
}