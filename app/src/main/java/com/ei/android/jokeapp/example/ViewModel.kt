package com.ei.android.jokeapp.example

import androidx.annotation.DrawableRes

class ViewModel(private val model: Model) {
    private var callBack: DataCallback? = null
    fun init(callback: DataCallback){
        this.callBack = callBack
        model.init(object : ResultCallback {
            override fun provide(data: Joke){
                callback?.let {
                    data.map(it)
                }
            }


        })
    }

    fun getJoke(){
        model.getJoke()
    }
    fun clear(){
        callBack = null
        model.clear()
    }

    fun chooseFavorites(checked: Boolean) {

    }
}

interface DataCallback{
    fun provideText(text: String)
    fun provideIconRes(@DrawableRes id:Int)
}