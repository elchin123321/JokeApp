package com.ei.android.jokeapp.example

interface CacheDataSource {
    suspend fun getJoke():Result<Joke,Unit>
    suspend fun addOrRemove(id: Int, joke: Joke):JokeUIModel
}