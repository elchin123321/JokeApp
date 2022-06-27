package com.ei.android.jokeapp.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.DrawableRes
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.views.CorrectButton
import com.ei.android.jokeapp.example.views.CorrectImageButton
import com.ei.android.jokeapp.example.views.CorrectProgress
import com.ei.android.jokeapp.example.views.CorrectTextView

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = (application as JokeApp).mainViewModel
        val button = findViewById<CorrectButton>(R.id.actionButton)
        val progressBar = findViewById<CorrectProgress>(R.id.progressBar)
        val textView = findViewById<CorrectTextView>(R.id.textView)
        progressBar.visibility = View.INVISIBLE
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            mainViewModel.chooseFavorites(isChecked)
        }
        val changeButton = findViewById<CorrectImageButton>(R.id.iconView)
        changeButton.setOnClickListener{
            mainViewModel.changeJokeStatus()
        }




        button.setOnClickListener{

            mainViewModel.getJoke()

        }

        mainViewModel.observe(this,{state->
            state.show(progressBar,button,textView, changeButton)
        })



    }

    override fun onDestroy() {

        super.onDestroy()
    }
}
interface Show<T>{
    fun show(arg:T)
}
interface ShowText:Show<String>
interface ShowImage:Show<Int>
interface ShowView:Show<Boolean>
interface EnableView{
    fun enable(enable:Boolean)
}
