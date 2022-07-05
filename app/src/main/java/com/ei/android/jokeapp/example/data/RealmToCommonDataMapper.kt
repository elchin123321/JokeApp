package com.ei.android.jokeapp.example.data

import io.realm.RealmObject

interface RealmToCommonDataMapper<T: RealmObject,E> {
    fun map(realmObject: T):CommonDataModel<E>
}

class JokeRealmToCommonMapper:RealmToCommonDataMapper<JokeRealmModel,Int>{
    override fun map(realmObject: JokeRealmModel) =
        CommonDataModel(realmObject.id,realmObject.question,realmObject.answer,true)

}

class QuoteRealmToCommonMapper:RealmToCommonDataMapper<QuoteRealmModel,String>{
    override fun map(realmObject: QuoteRealmModel) =
        CommonDataModel(realmObject.id,realmObject.content,realmObject.author,true)

}

