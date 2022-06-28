package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.core.Mapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm: RealmObject(), Mapper<JokeDataModel> {
    @PrimaryKey
    var id:Int = -1
    var question:String = ""
    var answer:String = ""

    override fun to() = JokeDataModel(id,question,answer,true)
}