package com.ei.android.jokeapp.example.data

import androidx.annotation.StringRes
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.ResourceManager
import com.ei.android.jokeapp.example.domain.NoCachedJokesException
import com.ei.android.jokeapp.example.domain.NoConnectionException
import com.ei.android.jokeapp.example.domain.ServiceUnavailableException
import java.lang.Exception

interface JokeFailure{
    fun getMessage(): String
}

class NoCachedJokes(private val resourceManager: ResourceManager): BaseJokeFailure(resourceManager) {
    override fun getMessageResId()= R.string.no_cached_jokes

    override fun getMessage() = resourceManager.getString(R.string.no_cached_jokes)
}
abstract class BaseJokeFailure(private val resourceManager: ResourceManager):JokeFailure{
    @StringRes
    protected abstract fun getMessageResId():Int
    override fun getMessage() = resourceManager.getString(getMessageResId())
}


class NoConnection(private val resourceManager: ResourceManager): BaseJokeFailure(resourceManager) {
    override fun getMessageResId()= R.string.no_connection

    override fun getMessage(): String = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager): BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.service_unaviable

    override fun getMessage(): String = resourceManager.getString(R.string.service_unaviable)
}

class GenericError(private val resourceManager: ResourceManager):BaseJokeFailure(resourceManager) {
    override fun getMessageResId()= R.string.generic_fail_message

    override fun getMessage() = resourceManager.getString(R.string.generic_fail_message)
}

interface JokeFailureHandler{
    fun handle(e:Exception):JokeFailure
}

class JokeFailureFactory(private val  resourceManager: ResourceManager):JokeFailureHandler{
    override fun handle(e: Exception)=when(e){
            is NoConnectionException -> NoConnection(resourceManager)
            is NoCachedJokesException -> NoCachedJokes(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else->GenericError(resourceManager)

    }

}