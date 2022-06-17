package com.ei.android.jokeapp.gender_detection_app.apiService

import com.ei.android.jokeapp.example.ServiceDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenderService {
    @GET("https://api.genderize.io")
    fun getGender(@Query("name") name:String): Call<GenderDTO>
}