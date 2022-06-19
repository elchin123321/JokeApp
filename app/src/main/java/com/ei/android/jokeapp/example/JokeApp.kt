package com.ei.android.jokeapp.example

import android.app.Application
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp: Application() {
    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        viewModel = ViewModel(BaseModel(BaseCachedDataSource(Realm.getDefaultInstance()),BaseCloudDataSource(retrofit.create(JokeService::class.java)),BaseResourceManager(this)))
    }
}
