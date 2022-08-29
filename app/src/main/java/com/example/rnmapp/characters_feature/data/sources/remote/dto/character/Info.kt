package com.example.rnmapp.characters_feature.data.sources.remote.dto.character

data class Info(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)