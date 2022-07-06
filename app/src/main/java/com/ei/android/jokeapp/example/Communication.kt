package com.ei.android.jokeapp.example

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication {
    fun showState(state: BaseViewModel.State)
    fun showDataList(list:List<CommonUIModel>)
    fun observe(owner:LifecycleOwner, observer: Observer<BaseViewModel.State>)
    fun isState(type: Int):Boolean
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>)
}

interface ListCommunication{
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>)
    fun showDataList(list:List<CommonUIModel>)
}

interface CommonCommunication:Communication, ListCommunication