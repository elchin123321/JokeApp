package com.ei.android.jokeapp.example.views

import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.BaseViewModel
import com.ei.android.jokeapp.example.CommonCommunication
import com.ei.android.jokeapp.example.JokeApp

class JokesFragment:BaseFragment<Int>() {
    override fun getViewModel(app: JokeApp) = app.baseViewModel

    override fun getCommunication(app: JokeApp) = app.jokeCommunication

    override fun actionButtonText() = R.string.show_favorite_joke

    override fun checkBoxText() = R.string.get_joke
}