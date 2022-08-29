package com.example.rnmapp.location_feature.domain.use_cases.get_location

import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.location_feature.data.sources.remote.dto.toLocation
import com.example.rnmapp.location_feature.domain.models.Location
import com.example.rnmapp.location_feature.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(locationId: String): Flow<Resource<Location>> = flow {
        try {
            emit(Resource.Loading<Location>())
            val location = repository.getLocationById(locationId).toLocation()
            emit(Resource.Success<Location>(location))
        } catch (e: HttpException) {
            emit(Resource.Error<Location>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Location>("Couldn't reach server. Check your internet connection."))
        }
    }
}