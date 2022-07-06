package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.RealmProvider
import com.ei.android.jokeapp.example.domain.NoCachedDataException
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class JokeCachedDataSource(
    realmProvider: RealmProvider,
    mapper:JokeRealmMapper,
    commonDataMapper:JokeRealmToCommonMapper):
    BaseCachedDataSource<JokeRealmModel, Int>(realmProvider,mapper,commonDataMapper){
        override val dbClass = JokeRealmModel::class.java
    override fun findRealmObject(realm: Realm, id: Int)=
        realm.where(dbClass).equalTo("id",id).findFirst()

}
class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    mapper:QuoteRealmMapper,
    commonDataMapper:QuoteRealmToCommonMapper):
    BaseCachedDataSource<QuoteRealmModel, String>(realmProvider,mapper,commonDataMapper){
    override val dbClass = QuoteRealmModel::class.java
    override fun findRealmObject(realm: Realm, id: String) =
        realm.where(dbClass).equalTo("id",id).findFirst()
}
abstract class BaseCachedDataSource<T: RealmObject, E>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T, E>,
    private val realmToCommonDataMapper: RealmToCommonDataMapper<T,E>
    ): CacheDataSource<E> {

    protected abstract val dbClass:Class<T>

    override suspend fun getData() = getRealmData {
        realmToCommonDataMapper.map(it.random())
     }

    override suspend fun getDataList() = getRealmData { results->
        results.map{realmToCommonDataMapper.map(it)}
    }

    private fun <R> getRealmData(block:(lis:RealmResults<T>)->R):R{
        realmProvider.provide().use {
            val list = it.where(dbClass).findAll()
            if (list.isEmpty())
                throw NoCachedDataException()
            else
                return block.invoke(list)
        }
    }


    protected abstract fun findRealmObject(realm: Realm, id:E):T?

    override suspend fun addOrRemove(id: E, model: CommonDataModel<E>): CommonDataModel<E> =
        withContext(Dispatchers.IO){
            realmProvider.provide().use{
                val itemRealm = findRealmObject(it,id)
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