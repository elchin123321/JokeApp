package com.ei.android.jokeapp.example

import com.ei.android.jokeapp.R

interface JokeFailure{
    fun getMessage(): String
}

class NoCachedJokes(private val resourceManager: ResourceManager):JokeFailure {
    override fun getMessage() = resourceManager.getString(R.string.no_cached_jokes)
}



class NoConnection(private val resourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.service_unaviable)
}