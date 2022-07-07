package com.ei.android.jokeapp.example

import android.app.Application
import com.ei.android.jokeapp.example.data.*
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp: Application() {
    lateinit var baseViewModel: BaseViewModel<Int>
    lateinit var quoteViewModel: BaseViewModel<String>
    lateinit var jokeCommunication: CommonCommunication<Int>

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
        val cacheDataSource =
            JokeCachedDataSource(realmProvider, JokeRealmMapper(), JokeRealmToCommonMapper())
        val cloudDataSource = JokeCloudDataSource(retrofit.create(NewJokeService::class.java))
        val jokeRepository = BaseRepository(cacheDataSource, cloudDataSource, BaseCachedData())
        val failureHandler = FailureFactory(BaseResourceManager(this))
        val mapper = CommonSuccessMapper<Int>()
        val interactor =
            BaseInteractor(jokeRepository, failureHandler, mapper)
        jokeCommunication = BaseCommunication()
        baseViewModel = BaseViewModel(interactor, jokeCommunication)
        //endregion
        val quoteRepository = BaseRepository(
            QuoteCachedDataSource(realmProvider, QuoteRealmMapper(), QuoteRealmToCommonMapper()),
            QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
            BaseCachedData()
        )
        val quoteMapper = CommonSuccessMapper<String>()
        quoteViewModel = BaseViewModel(
            BaseInteractor(quoteRepository, failureHandler, quoteMapper),
            BaseCommunication()
        )
    }
}
