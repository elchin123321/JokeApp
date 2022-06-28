package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.BaseJokeUIModel
import com.ei.android.jokeapp.example.FavoriteJokeUIModel
import com.ei.android.jokeapp.example.Joke
import com.ei.android.jokeapp.example.core.Mapper
import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("question")
    private val question: String,
    @SerializedName("id")
    private val id: Int,
    @SerializedName("answer")
    private val answer: String
):Mapper<JokeDataModel>{

    override fun to() = JokeDataModel(id, question , answer)
}
