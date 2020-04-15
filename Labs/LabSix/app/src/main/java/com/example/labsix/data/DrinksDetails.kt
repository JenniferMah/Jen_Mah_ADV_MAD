package com.example.labsix.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SearchResponse(
    val results: Drinks
)

@JsonClass(generateAdapter = true)
data class Drinks(
    val drinkSet: Set<DrinksDetails>
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
