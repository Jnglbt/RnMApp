package com.example.rnmapp.location_feature.presentation.location_list
import com.example.rnmapp.location_feature.domain.models.Location

data class LocationListState(
    val isLoading: Boolean = false,
    val locations: List<Location> = emptyList(),
    val next: String? = null,
    val previous: String? = null,
    val text: String = "",
    val hint: String = "Enter location",
    val isHintVisible: Boolean = true,
    val isNavigationVisible: Boolean = true,
    val error: String = ""
)
