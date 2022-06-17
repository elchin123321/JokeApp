package com.ei.android.jokeapp.gender_detection_app.resorceManager

import android.content.Context

class BaseResourceManager(private val context: Context):ResourceManager{
    override fun getString(stringResID: Int): String = context.getString(stringResID)
}