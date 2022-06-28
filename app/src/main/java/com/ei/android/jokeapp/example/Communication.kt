package com.ei.android.jokeapp.example

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication {
    fun showState(state: BaseViewModel.State)
    fun observe(owner:LifecycleOwner, observer: Observer<BaseViewModel.State>)
    fun isState(type: Int):Boolean
}