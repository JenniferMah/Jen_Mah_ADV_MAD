package com.example.happyhour.data.jokes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JokesDetails (
    val joke: String
)