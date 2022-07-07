package com.ei.android.jokeapp.example.data

import androidx.annotation.StringRes
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.ResourceManager
import com.ei.android.jokeapp.example.domain.NoCachedDataException
import com.ei.android.jokeapp.example.domain.NoConnectionException
import com.ei.android.jokeapp.example.domain.ServiceUnavailableException
import java.lang.Exception

interface Failure{
    fun getMessage(): String
}

class NoCachedJokes(private val resourceManager: ResourceManager): BaseFailure(resourceManager) {
    override fun getMessageResId()= R.string.no_cached_jokes

    override fun getMessage() = resourceManager.getString(R.string.no_cached_jokes)
}
abstract class BaseFailure(private val resourceManager: ResourceManager):Failure{
    @StringRes
    protected abstract fun getMessageResId():Int
    override fun getMessage() = resourceManager.getString(getMessageResId())
}


class NoConnection(private val resourceManager: ResourceManager): BaseFailure(resourceManager) {
    override fun getMessageResId()= R.string.no_connection

    override fun getMessage(): String = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager): BaseFailure(resourceManager) {
    override fun getMessageResId() = R.string.service_unaviable

    override fun getMessage(): String = resourceManager.getString(R.string.service_unaviable)
}

class GenericError(private val resourceManager: ResourceManager):BaseFailure(resourceManager) {
    override fun getMessageResId()= R.string.generic_fail_message

    override fun getMessage() = resourceManager.getString(R.string.generic_fail_message)
}

interface FailureHandler{
    fun handle(e:Exception):Failure
}

class FailureFactory(private val  resourceManager: ResourceManager):FailureHandler{
    override fun handle(e: Exception)=when(e){
            is NoConnectionException -> NoConnection(resourceManager)
            is NoCachedDataException -> NoCachedJokes(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else->GenericError(resourceManager)

    }

}