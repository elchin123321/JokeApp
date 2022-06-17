package com.ei.android.jokeapp.gender_detection_app.model

interface Model {
    fun init(callback: ResultCallback)
    fun detectGender(name: String)
    fun clear()
}