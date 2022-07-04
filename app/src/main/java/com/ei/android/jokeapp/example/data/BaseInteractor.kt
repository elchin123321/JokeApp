package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.CommonItem
import java.lang.Exception

class BaseInteractor(
    private val commonRepository: CommonRepository,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success>
): CommonInteractor {
    override suspend fun getItem(): CommonItem {
        return try {
            commonRepository.getCommonItem().map(mapper)
        }catch (e: Exception){
            CommonItem.Failed(failureHandler.handle(e))

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