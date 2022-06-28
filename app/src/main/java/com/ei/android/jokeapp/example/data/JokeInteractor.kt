package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.Joke

interface JokeInteractor {
    suspend fun getJoke(): Joke
    suspend fun changeFavorites(): Joke
    fun getFavoritesJokes(favorites: Boolean)
}