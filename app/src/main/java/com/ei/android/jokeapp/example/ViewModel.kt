package com.ei.android.jokeapp.example

class ViewModel(private val model: Model) {
    private var callBack: TextCallback? = null
    fun init(callback: TextCallback){
        this.callBack = callBack
        model.init(object : ResultCallback {
            override fun provideSuccess(data: Joke) = callback.provideText(data.getJokeUi())

            override fun provideError(error: JokeFailure) = callback.provideText(error.getMessage())

        })
    }

    fun getJoke(){
        model.getJoke()
    }
    fun clear(){
        callBack = null
        model.clear()
    }
}

interface TextCallback{
    fun provideText(text: String)
}