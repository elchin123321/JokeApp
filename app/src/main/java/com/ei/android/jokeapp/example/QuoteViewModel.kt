package com.ei.android.jokeapp.example

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class QuoteViewModel(private val communication: Communication):ViewModel(),CommonViewModel {
    override fun getItem() {
        TODO("Not yet implemented")
    }

    override fun changeItemStatus() {
        TODO("Not yet implemented")
    }

    override fun chooseFavorites(favorites: Boolean) {
        TODO("Not yet implemented")
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<BaseViewModel.State>) {
        communication.observe(owner, observer)
    }
}