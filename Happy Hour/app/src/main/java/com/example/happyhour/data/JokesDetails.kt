package com.example.happyhour.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JokesDetails (
    val joke: String
)