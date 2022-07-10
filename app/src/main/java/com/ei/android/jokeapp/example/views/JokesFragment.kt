package com.ei.android.jokeapp.example.views

import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.BaseViewModel
import com.ei.android.jokeapp.example.CommonCommunication
import com.ei.android.jokeapp.example.JokeApp
import com.ei.android.jokeapp.example.JokesViewModel

class JokesFragment:BaseFragment<JokesViewModel,Int>() {

    override fun actionButtonText() = R.string.show_favorite_joke

    override fun checkBoxText() = R.string.get_joke
    override fun getViewModelClass() = JokesViewModel::class.java
    override fun getViewModel(app: JokeApp) = app.baseViewModel

    override fun getCommunication(app: JokeApp) = app.jokeCommunication
}