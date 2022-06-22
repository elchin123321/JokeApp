package com.ei.android.jokeapp.example

class TestCloudDataSource {
    private var count = 0
     fun getJoke(callback: JokeCloudCallback) {
        val joke = JokeServerModel("question$count",count, "answer$count")
        callback.provide(joke.toJoke())
        count++
    }
}