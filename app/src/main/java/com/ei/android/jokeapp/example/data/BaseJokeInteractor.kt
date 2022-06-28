package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.Joke
import com.ei.android.jokeapp.example.ResourceManager
import com.ei.android.jokeapp.example.domain.NoCachedJokesException
import com.ei.android.jokeapp.example.domain.NoConnectionException
import com.ei.android.jokeapp.example.domain.ServiceUnavailableException
import java.lang.Exception

class BaseJokeInteractor(
    private val jokeRepository: JokeRepository,
    private val jokeFailureHandler: JokeFailureHandler,
    private val mapper: JokeDataModelMapper<Joke.Success>
): JokeInteractor {
    override suspend fun getJoke(): Joke {
        return try {
            jokeRepository.getJoke().map(mapper)
        }catch (e: Exception){
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavorites(): Joke {
        return try {
            jokeRepository.changeJokeStatus().map(mapper)
        }catch (e: Exception){
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override  fun getFavoritesJokes(favorites: Boolean) = jokeRepository.chooseDataSource(favorites)

}