package com.ei.android.jokeapp.example.views

import androidx.recyclerview.widget.DiffUtil
import com.ei.android.jokeapp.example.CommonUIModel

class CommonDiffUtilCallback<E>(
    private val oldList:List<CommonUIModel<E>>,
    private val newList:List<CommonUIModel<E>>
):DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].same(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = false
}