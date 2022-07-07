package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.CommonItem

interface CommonDataModelMapper<T,E> {
    fun map(id:E, first:String, second:String, cached:Boolean):T
}

class CommonSuccessMapper<E>:CommonDataModelMapper<CommonItem.Success<E>, E> {
    override fun map(id: E, first: String, second: String, cached: Boolean)=
        CommonItem.Success(id,first,second,cached)

}

class JokeRealmMapper:CommonDataModelMapper<JokeRealmModel, Int>{
    override fun map(id: Int, first: String, second: String, cached: Boolean)=
        JokeRealmModel().also { joke->
            joke.id = id
            joke.question = first
            joke.answer = second
        }
}
class QuoteRealmMapper:CommonDataModelMapper<QuoteRealmModel, String>{
    override fun map(id: String, first: String, second: String, cached: Boolean)=
        QuoteRealmModel().also { joke->
            joke.id = id
            joke.content = first
            joke.author = second

    }

}