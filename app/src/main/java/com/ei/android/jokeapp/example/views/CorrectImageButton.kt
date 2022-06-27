package com.ei.android.jokeapp.example.views

import android.content.Context
import android.util.AttributeSet
import com.ei.android.jokeapp.example.ShowImage

class CorrectImageButton:androidx.appcompat.widget.AppCompatImageButton, ShowImage {
    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet):super(context,attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr:Int):super(context,attrs, defStyleAttr)

    override fun show(arg: Int) {
        setImageResource(arg)
    }


}