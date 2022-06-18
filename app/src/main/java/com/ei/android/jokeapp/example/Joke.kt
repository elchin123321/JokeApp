package com.ei.android.jokeapp.example

import androidx.annotation.DrawableRes
import com.ei.android.jokeapp.R

abstract class Joke(private val text: String, private val punchline: String) {
    fun getJokeUi() = "$text\n$punchline"

    @DrawableRes
    abstract fun getIconResId(): Int

    fun map(callback: DataCallback) = callback.run {
        provideText(getJokeUi())
        provideIconRes(getIconResId())
    }
}

class BaseJoke(text: String, punchline: String): Joke(text, punchline) {
    override fun getIconResId() = R.drawable.heart
}
class FavoriteJoke(text: String, punchline: String): Joke(text, punchline) {
    override fun getIconResId() = R.drawable.heart_filled
}
class FailedJoke(text: String): Joke(text, "") {
    override fun getIconResId() = 0
}