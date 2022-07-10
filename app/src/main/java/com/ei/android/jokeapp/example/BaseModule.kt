package com.ei.android.jokeapp.example

import com.ei.android.jokeapp.example.data.*
import retrofit2.Retrofit

abstract class BaseModule<E,T:BaseViewModel<E>> {
    abstract fun getViewModel():T
    abstract fun getCommunications():BaseCommunication<E>
}
class JokesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit
):BaseModule<Int, JokesViewModel>(){
    private var communication:BaseCommunication<Int>? = null
    override fun getViewModel(): JokesViewModel {
        return JokesViewModel(getInteractor(), getCommunications())
    }

    override fun getCommunications(): BaseCommunication<Int> {
        if(communication == null)
            communication = BaseCommunication()
        return communication!!
    }

    private fun getInteractor()=
        BaseInteractor(getRepository(),failureHandler,CommonSuccessMapper())
    private fun getRepository() =
        BaseRepository(getCacheDataSource(),getCloudDataSource(), BaseCachedData())
    private fun getCacheDataSource() =
        JokeCachedDataSource(realmProvider,JokeRealmMapper(), JokeRealmToCommonMapper())
    private fun getCloudDataSource() =
        JokeCloudDataSource(retrofit.create(NewJokeService::class.java))

}
class QuotesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit):BaseModule<String, QuotesViewModel>(){
    private var communication:BaseCommunication<String>? = null
    override fun getViewModel(): QuotesViewModel {
        return QuotesViewModel(getInteractor(), getCommunications())
    }

    override fun getCommunications(): BaseCommunication<String> {
        if(communication == null)
            communication = BaseCommunication()
        return communication!!
    }

    private fun getInteractor()=
        BaseInteractor(getRepository(),failureHandler,CommonSuccessMapper())
    private fun getRepository() =
        BaseRepository(getCacheDataSource(),getCloudDataSource(), BaseCachedData())
    private fun getCacheDataSource() =
        QuoteCachedDataSource(realmProvider,QuoteRealmMapper(), QuoteRealmToCommonMapper())
    private fun getCloudDataSource() =
        QuoteCloudDataSource(retrofit.create(QuoteService::class.java))

}