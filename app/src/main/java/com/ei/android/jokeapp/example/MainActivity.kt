package com.ei.android.jokeapp.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.views.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: BaseViewModel<Int>

    lateinit var adapter:CommonDataRecyclerAdapter<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).baseViewModel
        val jokeCommunication = (application as JokeApp).jokeCommunication
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favoriteDataView)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this,{ state->
            favoriteDataView.show(state)
        })



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val observer: (t: List<CommonUIModel<Int>>) -> Unit = { _ ->
            adapter.update()
        }
        adapter = CommonDataRecyclerAdapter(object :
            CommonDataRecyclerAdapter.FavoriteItemClickListener<Int> {
            override fun change(id: Int) {
                Snackbar.make(
                    favoriteDataView,
                    R.string.remove_from_favorites,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    val position = viewModel.changeItemStatus(id,)
                    adapter.update()
                }.show()
            }
        }, jokeCommunication)
        recyclerView.adapter = adapter
        viewModel.observeList(this,observer)
        viewModel.getItemList()

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
