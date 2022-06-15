package com.ei.android.jokeapp

import android.content.Context
import androidx.annotation.StringRes

class BaseResourceManager(private val context: Context): ResourceManager{
    override fun getString(stringResID: Int): String = context.getString(stringResID)


}
interface ResourceManager{
    fun getString(@StringRes stringResID: Int):String
}