package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.core.Mapper
import com.google.gson.annotations.SerializedName

class NewJokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("setup")
    private val text:String,
    @SerializedName("delivery")
    private val punchline: String
): Mapper<JokeDataModel> {
    override fun to()= JokeDataModel(id,text,punchline)
}