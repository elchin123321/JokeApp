package com.ei.android.jokeapp.example

interface JokeCacheCallback {
    fun provide(joke: Joke)

    fun fail()
}