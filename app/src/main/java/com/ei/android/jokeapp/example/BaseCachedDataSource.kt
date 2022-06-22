package com.ei.android.jokeapp.example

import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext

class BaseCachedDataSource(private val realmProvider: RealmProvider): CacheDataSource {
    override suspend fun getJoke(): Result<Joke, Unit> {
        realmProvider.provide().use{
            val jokes = it.where(JokeRealm::class.java).findAll()
            if(jokes.isEmpty()){
                return Result.Error(Unit)
            }else{
                jokes.random().let{joke->
                    return Result.Success(
                        Joke(
                            joke.question,
                            joke.id,
                            joke.answer)
                    )
                }
            }
        }
    }

    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUIModel =
        withContext(Dispatchers.IO){
            Realm.getDefaultInstance().use{
                val jokeRealm =
                    it.where(JokeRealm::class.java).equalTo("id",id).findFirst()
                return@withContext if(jokeRealm == null){
                    it.executeTransaction { transition->
                        val newJoke = joke.toJokeRealm()
                        transition.insert(newJoke)
                    }
                    joke.toFavoriteJoke()
                }else{
                    it.executeTransaction{
                        jokeRealm.deleteFromRealm()
                    }
                    joke.toBaseJoke()
                }
            }
        }
}