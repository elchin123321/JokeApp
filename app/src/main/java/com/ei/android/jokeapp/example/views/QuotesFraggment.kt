package com.ei.android.jokeapp.example.views

import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.JokeApp

class QuotesFragment:BaseFragment<String>() {
    override fun getViewModel(app: JokeApp) = app.quoteViewModel

    override fun getCommunication(app: JokeApp) = app.quoteCommunication

    override fun actionButtonText() = R.string.show_favorite_quote

    override fun checkBoxText() = R.string.get_quote
}