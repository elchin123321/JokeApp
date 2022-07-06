package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.CommonItem
import java.lang.Exception

class BaseInteractor<E>(
    private val commonRepository: CommonRepository<E>,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success, E>
): CommonInteractor {
    override suspend fun getItem(): CommonItem {
        return try {
            commonRepository.getCommonItem().map(mapper)
        }catch (e: Exception){
            CommonItem.Failed(failureHandler.handle(e))

        }
    }

    override suspend fun getItemList(): List<CommonItem> {
        return try{
            commonRepository.getCommonItemList().map {
                it.map(mapper)
            }
        }catch (e:Exception){
            listOf(CommonItem.Failed(failureHandler.handle(e)))
        }
    }

    override suspend fun changeFavorites(): CommonItem {
        return try {
            commonRepository.changeStatus().map(mapper)
        }catch (e: Exception){
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override  fun getFavoritesJokes(favorites: Boolean) = commonRepository.chooseDataSource(favorites)

}