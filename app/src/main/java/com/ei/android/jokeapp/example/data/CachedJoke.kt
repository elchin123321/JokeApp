package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.Joke

interface CachedJoke: ChangeJoke {
    fun saveJoke(joke: JokeDataModel)
    fun clear()
}