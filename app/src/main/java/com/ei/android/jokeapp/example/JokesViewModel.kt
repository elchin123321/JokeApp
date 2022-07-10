package com.ei.android.jokeapp.example

import com.ei.android.jokeapp.example.data.CommonInteractor

class JokesViewModel(
    interactor:CommonInteractor<Int>,
    communication: CommonCommunication<Int>,
):BaseViewModel<Int>(interactor,communication) {
}
