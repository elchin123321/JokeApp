package com.ei.android.jokeapp.gender_detection_app.apiService

import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.gender_detection_app.resorceManager.ResourceManager


interface GenderFailure {
    fun getMessage():String
}

class NoConnection(private val resourceManager: ResourceManager): GenderFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager): GenderFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.service_unaviable)
}