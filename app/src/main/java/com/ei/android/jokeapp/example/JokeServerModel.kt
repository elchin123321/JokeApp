package com.ei.android.jokeapp.example

import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("answer")
    private val joke: String,
    @SerializedName("image")
    private val id:String,
    @SerializedName("forced")
    private val status:Boolean
){
    fun toJoke() = BaseJoke(joke,id)

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id,this)
    fun toFavoriteJoke() = FavoriteJoke(joke,id)

    fun toBaseJoke() = BaseJoke(joke,id)
}
