package com.example.rnmapp.location_feature.domain.use_cases.get_locations
import android.util.Log
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.toCharacter
import com.example.rnmapp.characters_feature.domain.models.Character
import com.example.rnmapp.characters_feature.domain.repository.CharacterRepository
import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeResponse
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.toEpisode
import com.example.rnmapp.episodes_feature.domain.models.Episode
import com.example.rnmapp.episodes_feature.domain.repository.EpisodeRepository
import com.example.rnmapp.location_feature.data.sources.remote.dto.LocationResponse
import com.example.rnmapp.location_feature.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLocationsUseCase
@Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(pageNumber: Int?): Flow<Resource<LocationResponse>> = flow {
        try {
            emit(Resource.Loading<LocationResponse>())
            val locations = repository.getLocations(pageNumber)
            emit(Resource.Success<LocationResponse>(locations))
        } catch (e: HttpException) {
            emit(
                Resource.Error<LocationResponse>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<LocationResponse>("Couldn't reach server. Check your internet connection."))
        }
    }
}