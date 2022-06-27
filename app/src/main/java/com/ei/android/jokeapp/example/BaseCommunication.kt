package com.ei.android.jokeapp.example

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class BaseCommunication:Communication {
    private val liveData = MutableLiveData<MainViewModel.State>()

    override fun showState(state: MainViewModel.State) {
        liveData.value = state
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<MainViewModel.State>) {
        liveData.observe(owner,observer)
    }
}