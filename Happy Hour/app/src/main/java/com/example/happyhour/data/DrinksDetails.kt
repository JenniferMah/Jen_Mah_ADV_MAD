package com.example.happyhour.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val drinks: Set<DrinksDetails>
)

@JsonClass(generateAdapter = true)
data class DrinksDetails (
    val strDrink: String,
    val strInstructions: String,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strDrinkThumb: String?
)
