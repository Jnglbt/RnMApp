package com.example.rnmapp.location_feature.presentation.location_list

import androidx.compose.ui.focus.FocusState

sealed class LocationListEvents {
    data class EnteredValue(val value: String): LocationListEvents()
    data class ChangeValueFocus(val focusState: FocusState): LocationListEvents()
}
