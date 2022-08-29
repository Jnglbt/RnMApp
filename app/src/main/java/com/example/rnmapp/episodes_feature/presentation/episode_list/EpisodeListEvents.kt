package com.example.rnmapp.episodes_feature.presentation.episode_list

import androidx.compose.ui.focus.FocusState

sealed class EpisodeListEvents {
    data class EnteredValue(val value: String): EpisodeListEvents()
    data class ChangeValueFocus(val focusState: FocusState): EpisodeListEvents()
}
