package com.ei.android.jokeapp.example.data

import io.realm.RealmObject

interface RealmToCommonDataMapper<T: RealmObject> {
    fun map(realmObject: T):CommonDataModel
}

class JokeRealmToCommonMapper:RealmToCommonDataMapper<JokeRealmModel>{
    override fun map(realmObject: JokeRealmModel) =
        CommonDataModel(realmObject.id,realmObject.question,realmObject.answer,true)

}

class QuoteRealmToCommonMapper:RealmToCommonDataMapper<QuoteRealmModel>{
    override fun map(realmObject: QuoteRealmModel) =
        CommonDataModel(realmObject.id,realmObject.content,realmObject.author,true)

}

