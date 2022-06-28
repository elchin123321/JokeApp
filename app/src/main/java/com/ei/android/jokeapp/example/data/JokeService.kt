package com.ei.android.jokeapp.example.data

import retrofit2.Call
import retrofit2.http.GET


interface JokeService {

    @GET("https://jservice.io/api/random")
    fun getJoke(): Call<List<JokeServerModel>>
}



