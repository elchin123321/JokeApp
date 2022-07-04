package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.core.Mapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealmModel: RealmObject() {
    @PrimaryKey
    var id:Int = -1
    var question:String = ""
    var answer:String = ""

    fun to() = CommonDataModel(id,question,answer,true)
}

open class QuoteRealmModel:RealmObject(){
    @PrimaryKey
    var id: Int = -1
    var content:String = ""
    var author:String = ""

    fun to() = CommonDataModel(id,content,author,true)
}