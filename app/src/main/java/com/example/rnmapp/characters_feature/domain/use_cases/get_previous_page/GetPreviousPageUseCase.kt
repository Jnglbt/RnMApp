package com.example.rnmapp.characters_feature.domain.use_cases.get_previous_page

import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterResponse
import com.example.rnmapp.characters_feature.domain.repository.CharacterRepository
import com.example.rnmapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPreviousPageUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(url: String): Flow<Resource<CharacterResponse>> = flow {
        try {
            emit(Resource.Loading<CharacterResponse>())
            val previous = repository.getPreviousPage(url)
            emit(Resource.Success<CharacterResponse>(previous))
        } catch(e: HttpException) {
            emit(Resource.Error<CharacterResponse>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<CharacterResponse>("Couldn't reach server. Check your internet connection."))
        }
    }
}