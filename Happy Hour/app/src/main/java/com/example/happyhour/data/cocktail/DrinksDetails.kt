package com.example.happyhour.data.cocktail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(

    val drinks: Set<DrinksDetails?>? = null
)

@JsonClass(generateAdapter = true)
data class DrinksDetails (
    var idDrink: String? = null,
    val strDrink: String? = null,
    val strInstructions: String? = null,
    val strIngredient1: String? = null,
    val strIngredient2: String? = null,
    val strIngredient3: String? = null,
    val strIngredient4: String? = null,
    val strIngredient5: String? = null,
    val strIngredient6: String? = null,

    val strMeasure1: String? = null,
    val strMeasure2: String? = null,
    val strMeasure3: String? = null,
    val strMeasure4: String? = null,
    val strMeasure5: String? = null,
    val strMeasure6: String? = null,

    val strDrinkThumb: String? = null
)
