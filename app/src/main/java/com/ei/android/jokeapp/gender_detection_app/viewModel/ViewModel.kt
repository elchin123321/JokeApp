package com.ei.android.jokeapp.gender_detection_app.viewModel

import com.ei.android.jokeapp.gender_detection_app.apiService.GenderFailure
import com.ei.android.jokeapp.gender_detection_app.model.Gender
import com.ei.android.jokeapp.gender_detection_app.model.Model
import com.ei.android.jokeapp.gender_detection_app.model.ResultCallback

class ViewModel(private val model: Model) {
    private var callback: TextCallback? = null
    fun init(callback: TextCallback){
       this.callback = callback
        model.init(object : ResultCallback{
            override fun provideSuccess(gender: Gender) = callback.provideText(gender.getGenderUI())

            override fun provideError(error: GenderFailure) = callback.provideText(error.getMessage())


        })
    }

    fun detectGender(name: String){
        model.detectGender(name)
    }
    fun clear(){
        model.clear()
        callback = null

    }
}


