package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.RealmProvider
import com.ei.android.jokeapp.example.core.Mapper
import com.ei.android.jokeapp.example.domain.NoCachedJokesException
import io.realm.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class JokeCachedDataSource(
    realmProvider: RealmProvider,
    mapper:JokeRealmMapper,
    commonDataMapper:JokeRealmToCommonMapper):
    BaseCachedDataSource<JokeRealmModel>(realmProvider,mapper,commonDataMapper){
        override val dbClass = JokeRealmModel::class.java
    }
class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    mapper:QuoteRealmMapper,
    commonDataMapper:QuoteRealmToCommonMapper):
    BaseCachedDataSource<QuoteRealmModel>(realmProvider,mapper,commonDataMapper){
    override val dbClass = QuoteRealmModel::class.java
}
abstract class BaseCachedDataSource<T: RealmObject>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T>,
    private val realmToCommonDataMapper: RealmToCommonDataMapper<T>
    ): CacheDataSource {

    protected abstract val dbClass:Class<T>
    override suspend fun getData(): CommonDataModel {
        realmProvider.provide().use{
            val list = it.where(dbClass).findAll()
            if(list.isEmpty()){
                throw NoCachedJokesException()
            }else{
                return realmToCommonDataMapper.map(list.random())
            }
        }
    }


    override suspend fun addOrRemove(id: Int, model: CommonDataModel): CommonDataModel =
        withContext(Dispatchers.IO){
            realmProvider.provide().use{
                val itemRealm =
                    it.where(dbClass).equalTo("id",id).findFirst()
                return@withContext if(itemRealm == null){
                    it.executeTransaction { transition->
                        val newData = model.map(mapper)
                        transition.insert(newData)
                    }
                    model.changeCached(true)
                }else{
                    it.executeTransaction{
                        itemRealm.deleteFromRealm()
                    }
                    model.changeCached(false)
                }
            }
        }
}