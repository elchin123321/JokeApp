package com.ei.android.jokeapp.example

interface ChangeJoke {
    suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeUIModel?
}