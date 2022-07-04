package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.core.Mapper
import retrofit2.Call
import retrofit2.http.GET


interface BaseJokeService{

    @GET("https://jservice.io/api/random")
    fun getJoke(): Call<JokeServerModel>


}

interface NewJokeService{
    @GET("https://v2.jokeapi.dev/joke/Any")
    fun getJoke() :Call<NewJokeServerModel>
}
interface QuoteService{
    @GET("https://api.quotable.io/random")
    fun getQuote():Call<QuoteServerModel>
}





