package com.ei.android.jokeapp.example

interface CloudDataSource {
    fun getJoke(callback:JokeCloudCallback)
}