package com.ei.android.jokeapp.example

import android.app.Application
import com.ei.android.jokeapp.example.data.*
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp: Application() {
    val viewModelsFactory by lazy {
        ViewModelsFactory(
            JokesModule(failureHandler, realmProvider, retrofit),
            QuotesModule(failureHandler, realmProvider, retrofit)
        )
    }
    private lateinit var retrofit:Retrofit
    private lateinit var realmProvider:RealmProvider
    private lateinit var failureHandler:FailureHandler

    lateinit var baseViewModel: BaseViewModel<Int>
    lateinit var quoteViewModel: BaseViewModel<String>
    lateinit var jokeCommunication: CommonCommunication<Int>
    lateinit var quoteCommunication: CommonCommunication<String>

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        realmProvider = BaseRealmProvider()
        failureHandler = FailureFactory(BaseResourceManager(this))


    }
}
