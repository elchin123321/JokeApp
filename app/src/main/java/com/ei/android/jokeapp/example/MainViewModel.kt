package com.ei.android.jokeapp.example

import androidx.annotation.DrawableRes
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val model: Model,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main):ViewModel() {


    fun getJoke() = viewModelScope.launch(dispatcher){
        communication.showState(State.Progress)
        model.getJoke().show(communication)
    }


    fun chooseFavorites(favorites: Boolean) = model.chooseDataSource(favorites)


    fun changeJokeStatus() = viewModelScope.launch(dispatcher){
        model.changeJokeStatus()?.let {
            communication.showState(State.Initial(it.getData().first,it.getData().second))
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observe(owner, observer)


    sealed class State{
         fun show(progress: ShowView,button: EnableView,textView: ShowText,imageButton: ShowImage
        ){
             show(progress, button)
             show(textView,imageButton)
         }
        protected open fun show(progress: ShowView,button: EnableView){}
        protected open fun show(textView: ShowText,imageButton: ShowImage){}
        object Progress:State() {
            override fun show(progress: ShowView, button: EnableView,
            ) {
                progress.show(true)
                button.enable(false)
            }
        }

        class Initial(private val text: String, @DrawableRes val id : Int):State() {
            override fun show(textView: ShowText, imageButton: ShowImage
            ) {
                textView.show(text)
                imageButton.show(id)
            }
            override fun show(progress: ShowView, button: EnableView,
            ) {
                progress.show(false)
                button.enable(true)
            }
        }
    }
}
