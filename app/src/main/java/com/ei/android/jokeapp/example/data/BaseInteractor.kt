package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.CommonItem
import java.lang.Exception

class BaseInteractor<E>(
    private val commonRepository: CommonRepository<E>,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success<E>, E>
): CommonInteractor<E> {
    override suspend fun getItem(): CommonItem<E> {
        return try {
            commonRepository.getCommonItem().map(mapper)
        }catch (e: Exception){
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override suspend fun getItemList(): List<CommonItem<E>> {
        return try{
            commonRepository.getCommonItemList().map {
                it.map(mapper)
            }
        }catch (e:Exception){
            listOf(CommonItem.Failed(failureHandler.handle(e)))
        }
    }

    override suspend fun changeFavorites(): CommonItem<E> {
        return try {
            commonRepository.changeStatus().map(mapper)
        }catch (e: Exception){
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override  fun getFavorites(favorites: Boolean) = commonRepository.chooseDataSource(favorites)
    override suspend fun removeItem(id: E) {
        commonRepository.removeItem(id)
    }

}