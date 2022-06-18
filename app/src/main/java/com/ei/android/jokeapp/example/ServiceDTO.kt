package com.ei.android.jokeapp.example

import com.google.gson.annotations.SerializedName

data class ServiceDTO(
    @SerializedName("answer")
    private val answer: String,
    @SerializedName("forced")
    private val forced:Boolean,
    @SerializedName("image")
    private val image:String
){
    fun toJoke() = BaseJoke(answer,image)
}
