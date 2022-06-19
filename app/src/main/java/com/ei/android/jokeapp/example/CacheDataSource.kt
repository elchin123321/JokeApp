package com.ei.android.jokeapp.example

interface CacheDataSource {
    fun getJoke(jokeCachedCallback: JokeCacheCallback)
    fun addOrRemove(id:String,jokeServerModel: JokeServerModel):Joke
}