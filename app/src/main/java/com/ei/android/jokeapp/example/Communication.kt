package com.ei.android.jokeapp.example

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication {
    fun showState(state: MainViewModel.State)
    fun observe(owner:LifecycleOwner, observer: Observer<MainViewModel.State>)
}