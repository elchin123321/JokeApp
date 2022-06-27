package com.ei.android.jokeapp.example.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import com.ei.android.jokeapp.example.ShowView

class CorrectProgress :ProgressBar, ShowView {
    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet):super(context,attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr:Int):super(context,attrs, defStyleAttr)

    override fun show(arg: Boolean) {
        visibility = if(arg) View.VISIBLE else View.INVISIBLE
    }


}