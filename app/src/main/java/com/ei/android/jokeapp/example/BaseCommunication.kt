package com.ei.android.jokeapp.example

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class BaseCommunication:CommonCommunication {
    private val liveData = MutableLiveData<BaseViewModel.State>()

    private val listLiveData = MutableLiveData<List<CommonUIModel>>()
    override fun showDataList(list: List<CommonUIModel>) {
        listLiveData.value = list
    }

    override fun showState(state: BaseViewModel.State) {
        liveData.value = state
    }



    override fun observe(owner: LifecycleOwner, observer: Observer<BaseViewModel.State>) {
        liveData.observe(owner,observer)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>) {
        listLiveData.observe(owner,observer)
    }

    override fun isState(type: Int) = liveData.value?.isType(type)?:false

}