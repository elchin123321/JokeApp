package com.ei.android.jokeapp.example

import retrofit2.Call
import retrofit2.http.GET


interface JokeService {

    @GET("https://yesno.wtf/api")
    fun getJoke(): Call<ServiceDTO>
}

interface ServiceCallback{

    fun returnSuccess(data: ServiceDTO)
    fun returnError(type: ErrorType)
}

enum class ErrorType{
    NO_CONNECTION,
    OTHER
}