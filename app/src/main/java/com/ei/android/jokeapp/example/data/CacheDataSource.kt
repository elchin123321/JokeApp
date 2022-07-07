package com.ei.android.jokeapp.example.data

interface CacheDataSource<E> : DataFetcher<E>, ChangeStatus<E>{
    suspend fun getDataList():List<CommonDataModel<E>>
    suspend fun remove(id:E)
}
