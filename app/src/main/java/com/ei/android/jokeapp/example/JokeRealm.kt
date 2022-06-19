package com.ei.android.jokeapp.example

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm: RealmObject(){
    @PrimaryKey
    var id:Int = -1
    var question:String = ""
    var answer:String = ""
}