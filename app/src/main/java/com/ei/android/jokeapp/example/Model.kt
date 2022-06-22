package com.ei.android.jokeapp.example

interface Model {
    suspend fun getJoke():JokeUIModel
    fun init(callback: JokeCallback)
    fun clear()
    suspend fun changeJokeStatus(): JokeUIModel?
    fun chooseDataSource(favorites: Boolean)
}

interface JokeCallback{
    fun provide(data: JokeUIModel)
}

