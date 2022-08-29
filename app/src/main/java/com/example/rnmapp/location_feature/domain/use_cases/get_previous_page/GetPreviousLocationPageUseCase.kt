package com.example.rnmapp.location_feature.domain.use_cases.get_previous_page

import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeResponse
import com.example.rnmapp.episodes_feature.domain.repository.EpisodeRepository
import com.example.rnmapp.location_feature.data.sources.remote.dto.LocationResponse
import com.example.rnmapp.location_feature.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPreviousLocationPageUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(url: String): Flow<Resource<LocationResponse>> = flow {
        try {
            emit(Resource.Loading<LocationResponse>())
            val previous = repository.getPreviousLocationPage(url)
            emit(Resource.Success<LocationResponse>(previous))
        } catch(e: HttpException) {
            emit(Resource.Error<LocationResponse>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<LocationResponse>("Couldn't reach server. Check your internet connection."))
        }
    }
}