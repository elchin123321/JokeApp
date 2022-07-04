package com.ei.android.jokeapp.example.data

interface CachedData: ChangeCommonItem {
    fun save(joke: CommonDataModel)
    fun clear()
}