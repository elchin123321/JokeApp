package com.ei.android.jokeapp.example.data

interface JokeDataFetcher {
    suspend fun getJoke(): JokeDataModel
}