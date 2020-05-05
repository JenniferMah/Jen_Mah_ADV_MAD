package com.example.happyhour.data.jokes

import retrofit2.Call
import retrofit2.http.GET

interface jokesDBService {
    //https://sv443.net/     jokeapi/v2/joke/Any?  contains=bar
    @GET("jokeapi/v2/joke/Any?contains=drink")
    fun searchJokes(): Call<JokesDetails>
}


