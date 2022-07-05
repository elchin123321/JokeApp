package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.core.Mapper
import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("question")
    private val question: String,
    @SerializedName("id")
    private val id: Int,
    @SerializedName("answer")
    private val answer: String
):Mapper<CommonDataModel<Int>>{

    override fun to() = CommonDataModel(id, question , answer)
}
