package com.ei.android.jokeapp

class Joke(private val text: String, private val punchline: String) {
    fun getJokeUi() = "$text\n$punchline"
}