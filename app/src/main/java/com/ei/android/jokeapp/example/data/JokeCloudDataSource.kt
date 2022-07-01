package com.ei.android.jokeapp.example.data

import retrofit2.Call

class JokeCloudDataSource(private val service: NewJokeService): BaseCloudDataSource<NewJokeServerModel>() {
    override fun getJokeServerModel() = service.getJoke()
}