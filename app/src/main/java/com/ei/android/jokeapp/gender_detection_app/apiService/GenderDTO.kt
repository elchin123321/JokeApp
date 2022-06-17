package com.ei.android.jokeapp.gender_detection_app.apiService

import com.ei.android.jokeapp.gender_detection_app.model.Gender
import com.google.gson.annotations.SerializedName

class GenderDTO(
    @SerializedName("name")
    private val name: String,
    @SerializedName("gender")
    private val gender: String?,

) {
    fun getGender():Gender{

        return Gender(gender?.toString() ?: "Failed to recognize")
    }
}