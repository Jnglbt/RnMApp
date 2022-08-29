package com.example.rnmapp.location_feature.presentation.location_detail

import com.example.rnmapp.characters_feature.domain.models.Character
import com.example.rnmapp.location_feature.domain.models.Location

data class LocationDetailState(
    val isLoading: Boolean = false,
    val location: Location? = null,
    val residents: MutableList<Character?> = mutableListOf(),
    val error: String = ""
)
