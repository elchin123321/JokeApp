package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.CommonItem

interface CommonInteractor<E> {
    suspend fun getItem(): CommonItem<E>
    suspend fun getItemList(): List<CommonItem<E>>
    suspend fun changeFavorites(): CommonItem<E>
    fun getFavorites(favorites: Boolean)
    suspend fun removeItem(id:E)

}