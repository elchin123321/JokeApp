package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.CommonItem

interface CommonDataModelMapper<T> {
    fun map(id:Int, first:String, second:String, cached:Boolean):T
}

class CommonSuccessMapper:CommonDataModelMapper<CommonItem.Success> {
    override fun map(id: Int, first: String, second: String, cached: Boolean)=
        CommonItem.Success(first,second,cached)

}

class JokeRealmMapper:CommonDataModelMapper<JokeRealmModel>{
    override fun map(id: Int, first: String, second: String, cached: Boolean)=
        JokeRealmModel().also { joke->
            joke.id = id
            joke.question = first
            joke.answer = second
        }
}
class QuoteRealmMapper:CommonDataModelMapper<QuoteRealmModel>{
    override fun map(id: Int, first: String, second: String, cached: Boolean)=
        QuoteRealmModel().also { joke->
            joke.id = id
            joke.content = first
            joke.author = second

    }

}