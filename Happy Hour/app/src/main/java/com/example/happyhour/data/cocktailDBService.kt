package com.example.happyhour.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface cocktailDBService{
   // https://www.thecocktaildb.com/    api/json/v1/1/search.php?    s=margarita
    @GET("api/json/v1/1/search.php?")
    fun searchCocktails(@Query("s")searchTerm: String): Call<SearchResponse>


}