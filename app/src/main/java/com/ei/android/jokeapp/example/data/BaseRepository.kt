package com.ei.android.jokeapp.example.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BaseRepository<E>(
    private val cacheDataSource: CacheDataSource<E>,
   private val cloudDataSource: CloudDataSource<E>,
    private val cached: CachedData<E>
): CommonRepository<E> {

    private var currentDataSource: DataFetcher<E> = cloudDataSource


    override suspend fun getCommonItem(): CommonDataModel<E> = withContext(Dispatchers.IO) {
       try {
           val data = currentDataSource.getData()
           cached.save(data)
           return@withContext data
       }catch (e:Exception){
           cached.clear()
           throw e
       }
    }

    override suspend fun changeStatus(): CommonDataModel<E> = cached.change(cacheDataSource)


    override fun chooseDataSource(favorites: Boolean) {
        currentDataSource = if(favorites) cacheDataSource else cloudDataSource
    }



}


