package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.JokeUIModel
import java.lang.IllegalStateException

interface ChangeJoke {
    suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel

    class Empty:ChangeJoke{
        override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
            throw IllegalStateException("empty change joke called")
        }
    }
}