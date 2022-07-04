package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.core.Mapper
import com.google.gson.annotations.SerializedName

data class QuoteServerModel(
    @SerializedName("_id")
    private val id:String,
    @SerializedName("content")
    private val content:String,
    @SerializedName("author")
    private val author:String
):Mapper<CommonDataModel> {
    override fun to() = CommonDataModel(System.currentTimeMillis().toInt(), content, author)
}