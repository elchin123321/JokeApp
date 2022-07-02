package com.ei.android.jokeapp.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.views.*

class MainActivity : AppCompatActivity() {

    lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        baseViewModel = (application as JokeApp).baseViewModel

        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favoriteDataView)

        favoriteDataView.listenChanges { isChecked ->
            baseViewModel.chooseFavorites(isChecked)
        }
        favoriteDataView.handleChangeButton {
            baseViewModel.changeJokeStatus()
        }
        favoriteDataView.handleActionButton {
            baseViewModel.getJoke()
        }

        baseViewModel.observe(this,{state->
            favoriteDataView.show(state)
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
