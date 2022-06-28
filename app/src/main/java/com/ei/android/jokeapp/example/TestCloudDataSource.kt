package com.ei.android.jokeapp.example

import com.ei.android.jokeapp.example.data.JokeServerModel

class TestCloudDataSource {
    private var count = 0
     fun getJoke() {
        val joke = JokeServerModel("question$count",count, "answer$count")
        //callback.provide(joke.toJoke())
        count++
    }
}