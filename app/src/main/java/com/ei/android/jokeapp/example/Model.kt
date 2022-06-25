package com.ei.android.jokeapp.example

interface Model {
    suspend fun getJoke():JokeUIModel
    suspend fun changeJokeStatus(): JokeUIModel?
    fun chooseDataSource(favorites: Boolean)
}


