package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.Joke
import com.ei.android.jokeapp.example.JokeUIModel

interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeDataModel
}