package com.example.rnmapp.characters_feature.presentation.character_list

import androidx.compose.ui.focus.FocusState

sealed class CharacterListEvents {
    data class EnteredValue(val value: String): CharacterListEvents()
    data class ChangeValueFocus(val focusState: FocusState): CharacterListEvents()
}
