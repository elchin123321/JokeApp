package com.ei.android.jokeapp.example

import com.ei.android.jokeapp.example.data.CommonInteractor

class QuotesViewModel(
    interactor: CommonInteractor<String>,
    communication: CommonCommunication<String>,
):BaseViewModel<String>(interactor,communication) {
}