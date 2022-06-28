package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.Joke
import com.ei.android.jokeapp.example.JokeUIModel

class JokeDataModel (
     private val id: Int,
     private val question: String,
     private val answer: String,
     private val cached:Boolean = false
):ChangeJoke
{
    fun <T> map(mapper: JokeDataModelMapper<T>):T{
         return mapper.map(id, question, answer, cached)
    }
     override suspend fun change(changeJokeStatus: ChangeJokeStatus) = changeJokeStatus.addOrRemove(id,this)


     fun changeCached(cached: Boolean) = JokeDataModel(id, question, answer, cached)

}