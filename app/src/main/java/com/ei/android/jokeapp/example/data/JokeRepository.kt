package com.ei.android.jokeapp.example.data


interface JokeRepository {
    suspend fun getJoke(): JokeDataModel
    suspend fun changeJokeStatus(): JokeDataModel
    fun chooseDataSource(favorites: Boolean)
}


