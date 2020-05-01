package com.example.happyhour.data

import com.squareup.moshi.JsonClass

//I just want to pull one joke at a time so I dont think I need this
//@JsonClass(generateAdapter = true)
//data class SearchResponse(
//    val jokeSet: Set<JokesDetails>
//)

@JsonClass(generateAdapter = true)
data class JokesDetails (
    val joke: String,
    val type: String
)