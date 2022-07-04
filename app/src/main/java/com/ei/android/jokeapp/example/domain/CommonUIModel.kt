package com.ei.android.jokeapp.example

import androidx.annotation.DrawableRes
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.core.Mapper
import com.ei.android.jokeapp.example.data.Failure

abstract class CommonUIModel(private val first: String, private val second: String) {
    protected open fun text() = "$first\n$second"

    @DrawableRes
    protected abstract fun getIconResId(): Int
    open fun show(communication: Communication) = communication.showState(BaseViewModel.State.Initial(text(),getIconResId()))


}

class BaseCommonUIModel(text: String, punchline: String): CommonUIModel(text, punchline) {
    override fun getIconResId() = R.drawable.heart
}
class FavoriteCommonUIModel(text: String, punchline: String): CommonUIModel(text, punchline) {
    override fun getIconResId() = R.drawable.heart_filled
}
class FailedCommonUIModel(private val text: String): CommonUIModel(text, "") {
    override fun text() = text
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        BaseViewModel.State.Failed(text(),getIconResId()))
}

sealed class CommonItem:Mapper<CommonUIModel>{
    class Success(
        private val firstText: String,
        private val secondText:String,
        private val favorite:Boolean
    ):CommonItem() {
        override fun to(): CommonUIModel {
            return if(favorite){
                FavoriteCommonUIModel(firstText,secondText)
            }else{
                BaseCommonUIModel(firstText,secondText)
            }
        }
    }
    class Failed(private val failure: Failure):CommonItem(){
        override fun to(): CommonUIModel {
            return FailedCommonUIModel(failure.getMessage())
        }
    }
}