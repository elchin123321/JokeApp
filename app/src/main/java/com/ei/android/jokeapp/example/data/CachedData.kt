package com.ei.android.jokeapp.example.data

interface CachedData<E>: ChangeCommonItem<E> {
    fun save(joke: CommonDataModel<E>)
    fun clear()
}