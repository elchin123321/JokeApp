package com.ei.android.jokeapp.example

import androidx.annotation.DrawableRes
import com.ei.android.jokeapp.R
import com.google.gson.annotations.SerializedName

abstract class JokeUIModel(private val text: String, private val punchline: String) {
    protected open fun text() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId(): Int
    fun getData() = Pair(text(),getIconResId())
    fun show(communication: Communication) = communication.showState(MainViewModel.State.Initial(text(),getIconResId()))


}

class BaseJokeUIModel(text: String, punchline: String): JokeUIModel(text, punchline) {
    override fun getIconResId() = R.drawable.heart
}
class FavoriteJokeUIModel(text: String, punchline: String): JokeUIModel(text, punchline) {
    override fun getIconResId() = R.drawable.heart_filled
}
class FailedJokeUIModel(private val text: String): JokeUIModel(text, "") {
    override fun text() = text


    override fun getIconResId() = 0
}

data class Joke(
    @SerializedName("question")
    private val question: String,
    @SerializedName("id")
    private val id: Int,
    @SerializedName("answer")
    private val answer: String
):ChangeJoke{
    fun toJoke() = BaseJokeUIModel(question,answer)

    override suspend fun change(changeJokeStatus: ChangeJokeStatus) = changeJokeStatus.addOrRemove(id,this)
    fun toFavoriteJoke() = FavoriteJokeUIModel(question,answer)

    fun toBaseJoke() = BaseJokeUIModel(question,answer)
    fun toJokeRealm(): JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.answer = answer
            it.question = question
        }
    }
}