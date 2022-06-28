package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.Joke

interface JokeDataModelMapper<T> {
    fun map(id:Int, question:String, answer:String, cached:Boolean):T
}

class JokeSuccessMapper:JokeDataModelMapper<Joke.Success> {
    override fun map(id: Int, question: String, answer: String, cached: Boolean)=
        Joke.Success(question,answer,cached)

}

class JokeRealmMapper:JokeDataModelMapper<JokeRealm>{
    override fun map(id: Int, question: String, answer: String, cached: Boolean)=
        JokeRealm().also { joke->
            joke.id = id
            joke.question = question
            joke.answer = answer
        }


}