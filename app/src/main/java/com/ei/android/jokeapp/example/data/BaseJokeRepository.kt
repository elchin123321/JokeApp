package com.ei.android.jokeapp.example.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BaseJokeRepository(
    private val cacheDataSource: CacheDataSource,
   private val cloudDataSource: CloudDataSource,
    private val cachedJoke: CachedJoke
): JokeRepository {

    private var currentDataSource: JokeDataFetcher = cloudDataSource


    override suspend fun getJoke(): JokeDataModel = withContext(Dispatchers.IO) {
       try {
           val joke = currentDataSource.getJoke()
           cachedJoke.saveJoke(joke)
           return@withContext joke
       }catch (e:Exception){
           cachedJoke.clear()
           throw e
       }
    }

    override suspend fun changeJokeStatus(): JokeDataModel = cachedJoke.change(cacheDataSource)


    override fun chooseDataSource(favorites: Boolean) {
        currentDataSource = if(favorites) cacheDataSource else cloudDataSource
    }



}
/*abstract class BaseResultHandler<S,E>
    (private val jokeDataFetcher: JokeDataFetcher<S, E>){
    suspend fun process(): JokeUIModel {
        return handleResult(jokeDataFetcher.getJoke())
    }

    protected abstract fun handleResult(result: Result<S, E>): JokeUIModel
}

class CloudResultHandler(
    private val cachedJoke: CachedJoke,
    jokeDataFetcher: JokeDataFetcher<JokeServerModel, ErrorType>,
    private val noConnection: JokeFailure,
    private val serviceUnavailable: JokeFailure
): BaseResultHandler<JokeServerModel, ErrorType>(jokeDataFetcher){

    override fun handleResult(result: Result<JokeServerModel, ErrorType>) = when(result) {
        is Result.Success<JokeServerModel> ->{
            result.data.toJoke().let {
                cachedJoke.saveJoke(it)
                it.toBaseJoke()
            }
        }
        is Result.Error<ErrorType> ->{
            cachedJoke.clear()
            val failure = if(result.exception == ErrorType.NO_CONNECTION)
                noConnection
            else serviceUnavailable
            FailedJokeUIModel(failure.getMessage())
        }
    }

}

class CacheResultHandler(
    private val cachedJoke: CachedJoke,
    jokeDataFetcher: JokeDataFetcher<Joke, Unit>,
    private val noCachedJokes: JokeFailure
): BaseResultHandler<Joke, Unit>(jokeDataFetcher){

    override fun handleResult(result: Result<Joke, Unit>) = when (result){
        is Result.Success<Joke> ->result.data.let {
            cachedJoke.saveJoke(it)
            it.toFavoriteJoke()

        }
        is Result.Error ->{
            cachedJoke.clear()
            FailedJokeUIModel(noCachedJokes.getMessage())

        }
    }*/

