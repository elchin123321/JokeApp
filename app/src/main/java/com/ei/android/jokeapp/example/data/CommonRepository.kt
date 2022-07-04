package com.ei.android.jokeapp.example.data


interface CommonRepository {
    suspend fun getCommonItem(): CommonDataModel
    suspend fun changeStatus(): CommonDataModel
    fun chooseDataSource(favorites: Boolean)
}


