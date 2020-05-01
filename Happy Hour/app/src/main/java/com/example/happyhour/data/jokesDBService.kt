package com.example.happyhour.data

import com.example.happyhour.data.cocktail.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface jokesDBService {
    //https://sv443.net/     jokeapi/v2/joke/Any?  contains=bar
    @GET("jokeapi/v2/joke/Any?")
    fun searchJokes(@Query("contains")searchTerm: String): Call<SearchResponse>
}


