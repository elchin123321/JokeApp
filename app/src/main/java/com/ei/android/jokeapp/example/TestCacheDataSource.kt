package com.ei.android.jokeapp.example

class TestCacheDataSource {
    private val map = HashMap<String, JokeServerModel>()
    private val list = ArrayList<Pair<Int,JokeServerModel>>()
     fun getJoke() {

            //jokeCachedCallback.provide(list.random().second.toJoke())
    }

    fun addOrRemove(id: Int, jokeServerModel: Joke): JokeUIModel {
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