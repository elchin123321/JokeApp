package com.ei.android.jokeapp.example

import android.util.Log
import io.realm.Realm

class BaseCachedDataSource(private val realm: Realm): CacheDataSource {
    override fun getJoke(jokeCachedCallback: JokeCacheCallback) {
        realm.let{
            val jokes = it.where(JokeRealm::class.java).findAll()
            if(jokes.isEmpty()){
                jokeCachedCallback.fail()
            }else{
                jokes.random().let{joke->
                    jokeCachedCallback.provide(
                        JokeServerModel(
                            joke.question,
                            joke.id,
                            joke.answer)
                    )
                }
            }
        }
    }

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): Joke {
        realm.let{
            val jokeRealm = it.where(JokeRealm::class.java).equalTo("id",id).findFirst()
            return if(jokeRealm == null){
                val newJoke = jokeServerModel.toJokeRealm()
                it.executeTransactionAsync{transition->
                    transition.insert(newJoke)
                }
                jokeServerModel.toFavoriteJoke()
            }else{
                it.executeTransactionAsync{
                    jokeRealm.deleteFromRealm()
                }
                jokeServerModel.toBaseJoke()
            }
        }
    }
}