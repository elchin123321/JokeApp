package com.ei.android.jokeapp.example

interface Model {
    fun getJoke()
    fun init(callback: JokeCallback)
    fun clear()
    fun changeJokeStatus(jokeCallback: JokeCallback)
    fun chooseDataSource(favorites: Boolean)
}

interface JokeCallback{
    fun provide(data: Joke)
}

