package com.ei.android.jokeapp.example

import retrofit2.Call
import retrofit2.http.GET


interface JokeService {

    @GET("https://jservice.io/api/random")
    fun getJoke(): Call<List<JokeServerModel>>
}



