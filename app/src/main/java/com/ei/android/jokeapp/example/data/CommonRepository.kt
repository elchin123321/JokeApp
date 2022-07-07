package com.ei.android.jokeapp.example.data


interface CommonRepository<E> {
    suspend fun getCommonItem(): CommonDataModel<E>
    suspend fun getCommonItemList():List<CommonDataModel<E>>
    suspend fun changeStatus(): CommonDataModel<E>
    fun chooseDataSource(favorites: Boolean)
    suspend fun removeItem(id:E)

}


