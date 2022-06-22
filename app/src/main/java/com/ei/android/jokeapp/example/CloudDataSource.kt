package com.ei.android.jokeapp.example

interface CloudDataSource {
    suspend fun getJoke(): Result<JokeServerModel, ErrorType>
}