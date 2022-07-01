package com.ei.android.jokeapp.example

import android.app.Application
import com.ei.android.jokeapp.example.data.*
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp: Application() {
    lateinit var baseViewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val cacheDataSource= BaseCachedDataSource(BaseRealmProvider(),JokeRealmMapper())
        val resourceManager = BaseResourceManager(this)
        val cloudDataSource = JokeCloudDataSource(retrofit.create(NewJokeService::class.java))
        val repository = BaseJokeRepository(cacheDataSource,cloudDataSource,BaseCachedJoke())
        val interractor = BaseJokeInteractor(repository,JokeFailureFactory(resourceManager),JokeSuccessMapper())
        baseViewModel = BaseViewModel(interractor,BaseCommunication())
    }
}
