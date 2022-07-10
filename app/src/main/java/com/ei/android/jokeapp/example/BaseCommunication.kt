package com.ei.android.jokeapp.example

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.ei.android.jokeapp.example.views.CommonDiffUtilCallback

class BaseCommunication<T> : CommonCommunication<T> {
    private val liveData = MutableLiveData<BaseViewModel.State>()
    override fun isState(type: Int) = liveData.value?.isType(type) ?: false
    override fun showState(state: BaseViewModel.State) {
        liveData.value = state
    }

    private val listLiveData = MutableLiveData<ArrayList<CommonUIModel<T>>>()
   /* override fun removeItem(id: T): Int {
        val found = listLiveData.value?.find {
            it.matches(id)
        }
        val position = listLiveData.value?.indexOf(found) ?: -1
        found?.let {
            listLiveData.value?.remove(it)
        }
        return position
    }*/

    override fun showDataList(list: List<CommonUIModel<T>>) {
        val callback = CommonDiffUtilCallback(listLiveData.value?: emptyList(),list)
        diffResult = DiffUtil.calculateDiff(callback)
        listLiveData.value = ArrayList(list)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel<T>>>) {
        listLiveData.observe(owner, observer)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<BaseViewModel.State>) =
        liveData.observe(owner, observer)

    override fun getList(): List<CommonUIModel<T>> {
        return listLiveData.value ?: emptyList()
    }

    private lateinit var diffResult: DiffUtil.DiffResult
    override fun getDiffResult() = diffResult
}