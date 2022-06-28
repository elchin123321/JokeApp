package com.ei.android.jokeapp.example

import android.app.Application
import com.ei.android.jokeapp.example.data.*
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp: Application() {
    lateinit var baseViewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val cacheDataSource= BaseCachedDataSource(BaseRealmProvider(),JokeRealmMapper())
        val resourceManager = BaseResourceManager(this)
        val cloudDataSource = BaseCloudDataSource(retrofit.create(JokeService::class.java))
        val repository = BaseJokeRepository(cacheDataSource,cloudDataSource,BaseCachedJoke())
        val interractor = BaseJokeInteractor(repository,JokeFailureFactory(resourceManager),JokeSuccessMapper())
        baseViewModel = BaseViewModel(interractor,BaseCommunication())
    }
}
