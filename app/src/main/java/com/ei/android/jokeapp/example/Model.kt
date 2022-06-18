package com.ei.android.jokeapp.example

interface Model {
    fun getJoke()
    fun init(callback: ResultCallback)
    fun clear()
}

interface ResultCallback{
    fun provide(data: Joke)
}

