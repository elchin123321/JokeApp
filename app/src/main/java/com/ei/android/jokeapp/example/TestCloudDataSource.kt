package com.ei.android.jokeapp.example

class TestCloudDataSource:CloudDataSource {
    private var count = 0
    override fun getJoke(callback: JokeCloudCallback) {
        val joke = JokeServerModel("question$count",count, "answer$count")
        callback.provide(joke)
        count++
    }
}