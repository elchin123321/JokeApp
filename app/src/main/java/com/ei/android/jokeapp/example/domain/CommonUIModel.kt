package com.ei.android.jokeapp.example

import androidx.annotation.DrawableRes
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.core.Mapper
import com.ei.android.jokeapp.example.data.Failure
import com.ei.android.jokeapp.example.views.CommonDataRecyclerAdapter

abstract class CommonUIModel<T>(private val first: String, private val second: String) {
    protected open fun text() = "$first\n$second"

    @DrawableRes
    protected abstract fun getIconResId(): Int
    open fun show(communication: Communication) =
        communication.showState(BaseViewModel.State.Initial(text(),getIconResId()))
    fun show(showText: ShowText) = showText.show(text())
    open fun change(listener:CommonDataRecyclerAdapter.FavoriteItemClickListener<T>) = Unit
    open fun matches(id:T) : Boolean = false
    open fun same(model:CommonUIModel<T>):Boolean = false

}

class BaseCommonUIModel<E>(text: String, punchline: String): CommonUIModel<E>(text, punchline) {
    override fun getIconResId() = R.drawable.heart
}
class FavoriteCommonUIModel<E>(private val id:E, text: String, punchline: String): CommonUIModel<E>(text, punchline) {
    override fun getIconResId() = R.drawable.heart_filled
    override fun change(listener: CommonDataRecyclerAdapter.FavoriteItemClickListener<E>) =
        listener.change(id)

    override fun matches(id: E): Boolean = this.id == id
    override fun same(model: CommonUIModel<E>): Boolean {
        return model is FavoriteCommonUIModel<E> && model.id == id
    }
}
class FailedCommonUIModel<E>(private val text: String): CommonUIModel<E>(text, "") {
    override fun text() = text
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        BaseViewModel.State.Failed(text(),getIconResId()))
}

sealed class CommonItem<E>:Mapper<CommonUIModel<E>>{
    class Success<E>(
        private val id:E,
        private val firstText: String,
        private val secondText:String,
        private val favorite:Boolean
    ) : CommonItem<E>() {
        override fun to() =
            if(favorite){
                FavoriteCommonUIModel(id,firstText,secondText)
            }else{
                BaseCommonUIModel(firstText,secondText)
            }
        }

    class Failed<E>(private val failure: Failure):CommonItem<E>(){
        override fun to():CommonUIModel<E> = FailedCommonUIModel(failure.getMessage())

    }
}