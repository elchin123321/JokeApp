package com.ei.android.jokeapp.example

import android.app.Application
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp: Application() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val cachedJoke = BaseCachedJoke()
        val cacheDataSource= BaseCachedDataSource(BaseRealmProvider())
        val resourceManager = BaseResourceManager(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mainViewModel = MainViewModel(
            BaseModel(
                cacheDataSource,
                CacheResultHandler(
                    cachedJoke,
                    cacheDataSource,
                    NoCachedJokes(resourceManager)
                ),
                CloudResultHandler(
                    cachedJoke,
                    BaseCloudDataSource(retrofit.create(JokeService::class.java)),
                    NoConnection(resourceManager),
                    ServiceUnavailable(resourceManager)
                ),
                cachedJoke
            ),
            BaseCommunication()
        )
    }
}
