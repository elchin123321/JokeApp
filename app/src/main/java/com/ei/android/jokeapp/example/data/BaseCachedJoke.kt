package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.Joke
import com.ei.android.jokeapp.example.JokeUIModel

class BaseCachedJoke: CachedJoke {
    private var cached: ChangeJoke = ChangeJoke.Empty()
    override fun saveJoke(joke: JokeDataModel) {
        cached = joke
    }

    override fun clear() {
        cached = ChangeJoke.Empty()
    }

    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
        return cached.change(changeJokeStatus)
    }
}