package com.ei.android.jokeapp.example

import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("question")
    private val question: String,
    @SerializedName("id")
    private val id: Int,
    @SerializedName("answer")
    private val answer: String
){
    fun toJoke() = Joke(question,id,answer)

    //fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id,this.toJoke())
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
