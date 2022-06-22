package com.ei.android.jokeapp.example

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(private val model: Model):ViewModel() {
    private var dataCallback: DataCallback? = null
    private val jokeCallback = object : JokeCallback{
        override fun provide(data: JokeUIModel) {
            dataCallback?.let{
                data.map(it)
            }
        }

    }
    fun init(callback: DataCallback){
        dataCallback = callback
        model.init(jokeCallback)

    }

    fun getJoke() = viewModelScope.launch{
        val uiModel = model.getJoke()
        dataCallback?.let {
            uiModel.map(it)
        }
    }
    fun clear(){
        dataCallback = null
        model.clear()
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)
    }

    fun changeJokeStatus() = viewModelScope.launch{
        val uiModel = model.changeJokeStatus()
        dataCallback?.let{
            uiModel?.map(it)
        }
    }
}

interface DataCallback{
    fun provideText(text: String)
    fun provideIconRes(@DrawableRes id:Int)
}