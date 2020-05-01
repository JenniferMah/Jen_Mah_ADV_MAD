package com.example.happyhour.data.cocktail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val drinks: Set<DrinksDetails>
)

@JsonClass(generateAdapter = true)
data class DrinksDetails (
    val idDrink: String, //this is shown as a string
    val strDrink: String,
    val strInstructions: String,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,

    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,


    val strDrinkThumb: String?
)
