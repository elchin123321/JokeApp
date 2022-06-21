package com.ei.android.jokeapp.example

class TestCacheDataSource:CacheDataSource {
    private val map = HashMap<String, JokeServerModel>()
    private val list = ArrayList<Pair<Int,JokeServerModel>>()
    override fun getJoke(jokeCachedCallback: JokeCacheCallback) {
        if(list.isEmpty())
            jokeCachedCallback.fail()
        else
            jokeCachedCallback.provide(list.random().second.toJoke())
    }

    override fun addOrRemove(id: Int, jokeServerModel: Joke): JokeUIModel {
        val found = list.find{it.first == id}
        return if(found!=null){
            val joke = found.second.toBaseJoke()
            list.remove(found)
            joke
        }else{
            //list.add(Pair(id,jokeServerModel))
            jokeServerModel.toFavoriteJoke()
        }
    }
}