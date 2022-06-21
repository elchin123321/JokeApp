package com.ei.android.jokeapp.example

interface JokeCloudCallback {
    fun provide(joke:Joke)
    fun fail(error: ErrorType)
}
enum class ErrorType{
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}