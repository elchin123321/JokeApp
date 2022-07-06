package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.CommonItem

interface CommonInteractor {
    suspend fun getItem(): CommonItem
    suspend fun getItemList(): List<CommonItem>
    suspend fun changeFavorites(): CommonItem
    fun getFavoritesJokes(favorites: Boolean)
}