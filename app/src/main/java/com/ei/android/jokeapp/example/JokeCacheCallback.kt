package com.ei.android.jokeapp.example

interface JokeCacheCallback {
    fun provide(jokeServerModel: JokeServerModel)

    fun fail()
}