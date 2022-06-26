package com.ei.android.jokeapp.example

interface JokeDataFetcher<S, E> {
    suspend fun getJoke(): Result<S, E>
}