package com.ei.android.jokeapp.gender_detection_app.resorceManager

import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes stringResID: Int):String
}