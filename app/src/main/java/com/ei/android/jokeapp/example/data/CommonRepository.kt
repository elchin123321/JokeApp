package com.ei.android.jokeapp.example.data


interface CommonRepository<E> {
    suspend fun getCommonItem(): CommonDataModel<E>
    suspend fun changeStatus(): CommonDataModel<E>
    fun chooseDataSource(favorites: Boolean)
}


