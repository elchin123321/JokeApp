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
    lateinit var quoteViewModel: BaseViewModel

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


        val realmProvider = BaseRealmProvider()
        val cacheDataSource= JokeCachedDataSource(realmProvider,JokeRealmMapper(),
            JokeRealmToCommonMapper()
        )
        val resourceManager = BaseResourceManager(this)
        val failureHandler = FailureFactory(resourceManager)
        val mapper = CommonSuccessMapper()
        val cloudDataSource = JokeCloudDataSources(retrofit.create(NewJokeService::class.java))
        val jokeRepository = BaseRepository(cacheDataSource,cloudDataSource,BaseCachedData())
        val interractor = BaseInteractor(jokeRepository,failureHandler,mapper)
        baseViewModel = BaseViewModel(interractor,BaseCommunication())

        val quoteRepository = BaseRepository(
            QuoteCachedDataSource(realmProvider, QuoteRealmMapper(),QuoteRealmToCommonMapper()),
            QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
            BaseCachedData()
        )
        quoteViewModel = BaseViewModel(
            BaseInteractor(quoteRepository, failureHandler,mapper),
            BaseCommunication()
        )
    }
}
