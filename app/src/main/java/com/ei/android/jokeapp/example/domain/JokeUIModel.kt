package com.ei.android.jokeapp.example

import androidx.annotation.DrawableRes
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.core.Mapper
import com.ei.android.jokeapp.example.data.JokeFailure

abstract class JokeUIModel(private val text: String, private val punchline: String) {
    protected open fun text() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId(): Int
    open fun show(communication: Communication) = communication.showState(BaseViewModel.State.Initial(text(),getIconResId()))


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
    override fun show(communication: Communication) = communication.showState(
        BaseViewModel.State.Failed(text(),getIconResId()))
}

sealed class Joke:Mapper<JokeUIModel>{
    class Success(
        private val question: String,
        private val answer:String,
        private val favorite:Boolean
    ):Joke() {
        override fun to(): JokeUIModel {
            return if(favorite){
                FavoriteJokeUIModel(question,answer)
            }else{
                BaseJokeUIModel(question,answer)
            }
        }
    }
    class Failed(private val failure: JokeFailure):Joke(){
        override fun to(): JokeUIModel {
            return FailedJokeUIModel(failure.getMessage())
        }
    }
}