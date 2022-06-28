package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.Joke
import com.ei.android.jokeapp.example.JokeUIModel
import com.ei.android.jokeapp.example.RealmProvider
import com.ei.android.jokeapp.example.domain.NoCachedJokesException
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCachedDataSource(
    private val realmProvider: RealmProvider,
    private val mapper: JokeDataModelMapper<JokeRealm>
    ): CacheDataSource {
    override suspend fun getJoke(): JokeDataModel {
        realmProvider.provide().use{
            val jokes = it.where(JokeRealm::class.java).findAll()
            if(jokes.isEmpty()){
                throw NoCachedJokesException()
            }else{
                return jokes.random().to()
            }
        }
    }


    override suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeDataModel =
        withContext(Dispatchers.IO){
            realmProvider.provide().use{
                val jokeRealm =
                    it.where(JokeRealm::class.java).equalTo("id",id).findFirst()
                return@withContext if(jokeRealm == null){
                    it.executeTransaction { transition->
                        val newJoke = joke.map(mapper)
                        transition.insert(newJoke)
                    }
                    joke.changeCached(true)
                }else{
                    it.executeTransaction{
                        jokeRealm.deleteFromRealm()
                    }
                    joke.changeCached(false)
                }
            }
        }
}