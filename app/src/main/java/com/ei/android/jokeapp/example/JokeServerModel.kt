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
    fun toJoke() = BaseJoke(question,answer)

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id,this)
    fun toFavoriteJoke() = FavoriteJoke(question,answer)

    fun toBaseJoke() = BaseJoke(question,answer)
    fun toJokeRealm(): JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.answer = answer
            it.question = question
        }
    }
}
