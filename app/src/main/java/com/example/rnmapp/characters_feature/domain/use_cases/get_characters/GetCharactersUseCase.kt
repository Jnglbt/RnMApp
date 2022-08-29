package com.example.rnmapp.characters_feature.domain.use_cases.get_characters

import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterResponse
import com.example.rnmapp.characters_feature.domain.repository.CharacterRepository
import com.example.rnmapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(pageNumber: Int?): Flow<Resource<CharacterResponse>> = flow {
        try {
            emit(Resource.Loading<CharacterResponse>())
            val characters = repository.getCharacters(pageNumber)
            emit(Resource.Success<CharacterResponse>(characters))
        } catch(e: HttpException) {
            emit(Resource.Error<CharacterResponse>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<CharacterResponse>("Couldn't reach server. Check your internet connection."))
        }
    }
}