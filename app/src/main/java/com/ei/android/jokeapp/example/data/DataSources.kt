package com.ei.android.jokeapp.example.data

import retrofit2.Call

class JokeCloudDataSources(private val service: NewJokeService): BaseCloudDataSource<NewJokeServerModel>() {
    override fun getServerModel() = service.getJoke()
}
class QuoteCloudDataSource(private val service: QuoteService):BaseCloudDataSource<QuoteServerModel>(){
    override fun getServerModel() = service.getQuote()

}