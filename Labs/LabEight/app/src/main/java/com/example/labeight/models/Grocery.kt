package com.example.labeight.models
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroceryItem(
    val id: Int,
    val location: String,
    val name: String,
    val quantity: Int
)