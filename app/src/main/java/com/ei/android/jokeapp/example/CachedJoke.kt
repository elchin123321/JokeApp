package com.ei.android.jokeapp.example

interface CachedJoke: ChangeJoke {
    fun saveJoke(joke: Joke)
    fun clear()
}