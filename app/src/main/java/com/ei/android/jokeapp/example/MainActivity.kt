package com.ei.android.jokeapp.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.views.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: BaseViewModel
    lateinit var quoteViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).baseViewModel

        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favoriteDataView)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this,{ state->
            favoriteDataView.show(state)
        })

        quoteViewModel = (application as JokeApp).quoteViewModel
        val quoteFavoriteDataView = findViewById<FavoriteDataView>(R.id.quoteFavoriteView)
        quoteFavoriteDataView.linkWith(quoteViewModel)
        quoteViewModel.observe(this,{state->
            quoteFavoriteDataView.show(state)})


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CommonDataRecyclerAdapter()
        recyclerView.adapter = adapter
        quoteViewModel.observerList(this,{ list->
            adapter.show(list)
        })
        quoteViewModel.getItemList()

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
