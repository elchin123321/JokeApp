package com.ei.android.jokeapp.example

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication {
    fun showState(state: BaseViewModel.State)

    fun observe(owner:LifecycleOwner, observer: Observer<BaseViewModel.State>)
    fun isState(type: Int):Boolean

}

interface ListCommunication<T>{
    fun getList():List<CommonUIModel<T>>
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel<T>>>)
    fun showDataList(list:List<CommonUIModel<T>>)
    fun removeItem(id:T): Int
}

interface CommonCommunication<T>:Communication, ListCommunication<T>