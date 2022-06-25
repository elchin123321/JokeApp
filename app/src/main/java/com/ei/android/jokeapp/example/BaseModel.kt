package com.ei.android.jokeapp.example

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourceManager: ResourceManager
): Model {

    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
    private val noCachedJokes by lazy{NoCachedJokes(resourceManager)}



    private var cachedJoke: Joke? = null
    private var getJokeFromCache = false

    override suspend fun getJoke():JokeUIModel {
        if(getJokeFromCache){
            return when(val result = cacheDataSource.getJoke()){
                is Result.Success<Joke>->result.data.let{
                    cachedJoke = it
                    it.toFavoriteJoke()
                }
                is Result.Error->{
                    cachedJoke = null
                    FailedJokeUIModel(noCachedJokes.getMessage())
                }
            }
        }else{
            return when(val result = cloudDataSource.getJoke()){
                is Result.Success<JokeServerModel>->{
                    result.data.toJoke().let{
                        cachedJoke = it
                        it.toBaseJoke()
                    }
                }
                is Result.Error<ErrorType>->{
                    cachedJoke = null
                    val failure = if(result.exception == ErrorType.NO_CONNECTION)
                        noConnection
                    else serviceUnavailable
                    FailedJokeUIModel(failure.getMessage())
                }
            }
        }
    }





    override suspend fun changeJokeStatus():JokeUIModel? = cachedJoke?.change(cacheDataSource)


    override fun chooseDataSource(favorites: Boolean) {
        getJokeFromCache = favorites
    }
}