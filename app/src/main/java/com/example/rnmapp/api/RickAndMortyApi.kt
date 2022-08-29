package com.example.rnmapp.api

import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterDto
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterResponse
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeDto
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.EpisodeResponse
import com.example.rnmapp.location_feature.data.sources.remote.dto.LocationDto
import com.example.rnmapp.location_feature.data.sources.remote.dto.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RickAndMortyApi {

    //Character
    @GET("character/")
    suspend fun getCharacters(@Query("page") pageNumber: Int?): CharacterResponse

    @GET("character/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: String): CharacterDto

    @GET
    suspend fun getNextPage(@Url url: String) : CharacterResponse

    @GET
    suspend fun getPreviousPage(@Url url: String) : CharacterResponse

    @GET("character/")
    suspend fun getCharactersByName(@Query("name") name: String): CharacterResponse

    @GET
    suspend fun getCharacterByUrl(@Url url: String): CharacterDto

    //Episode
    @GET("episode/")
    suspend fun getEpisodes(@Query("page") pageNumber: Int?): EpisodeResponse

    @GET("episode/{episodeId}")
    suspend fun getEpisodeById(@Path("episodeId") episodeId: String): EpisodeDto

    @GET
    suspend fun getNextEpisodePage (@Url url: String) : EpisodeResponse

    @GET
    suspend fun getPreviousEpisodePage (@Url url: String) : EpisodeResponse

    @GET("episode/")
    suspend fun getEpisodesByCode(@Query("episode") episode: String): EpisodeResponse

    @GET
    suspend fun getEpisodeByUrl(@Url url: String): EpisodeDto

    //Location
    @GET("location/")
    suspend fun getLocations(@Query("page") pageNumber: Int?): LocationResponse

    @GET("location/{locationId}")
    suspend fun getLocationById(@Path("locationId") locationId: String): LocationDto

    @GET
    suspend fun getNextLocationPage (@Url url: String) : LocationResponse

    @GET
    suspend fun getPreviousLocationPage (@Url url: String) : LocationResponse

    @GET("location/")
    suspend fun getLocationByName(@Query("name") name: String): LocationResponse

    @GET
    suspend fun getLocationByUrl(@Url url: String): LocationDto
}