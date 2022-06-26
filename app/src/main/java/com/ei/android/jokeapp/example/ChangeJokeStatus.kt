package com.ei.android.jokeapp.example

interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: Joke): JokeUIModel
}