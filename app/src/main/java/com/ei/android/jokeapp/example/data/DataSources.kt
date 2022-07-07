package com.ei.android.jokeapp.example.data

import retrofit2.Call

class JokeCloudDataSource(private val service: NewJokeService): BaseCloudDataSource<NewJokeServerModel, Int>() {
    override fun getServerModel() = service.getJoke()
}
class QuoteCloudDataSource(private val service: QuoteService):BaseCloudDataSource<QuoteServerModel,String>(){
    override fun getServerModel() = service.getQuote()

}