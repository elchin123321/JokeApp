package com.ei.android.jokeapp.example.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.BaseViewModel
import com.ei.android.jokeapp.example.CommonCommunication
import com.ei.android.jokeapp.example.JokeApp
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<V:BaseViewModel<T>,T>: Fragment() {
    private lateinit var viewModel: BaseViewModel<T>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            (requireActivity().application as JokeApp).viewModelsFactory
        ).get(getViewModelClass())

    }
    protected abstract fun getViewModelClass():Class<V>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_fragment,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val favoriteDataView = view.findViewById<FavoriteDataView>(R.id.favoriteDataView)
        favoriteDataView.linkWith(viewModel)
        favoriteDataView.checkBoxText(checkBoxText())
        favoriteDataView.actionButtonText(actionButtonText())
        viewModel.observe(this,{ state->
            favoriteDataView.show(state)
        })
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CommonDataRecyclerAdapter(object :
            CommonDataRecyclerAdapter.FavoriteItemClickListener<T> {
            override fun change(id: T) {
                Snackbar.make(
                    favoriteDataView,
                    R.string.remove_from_favorites,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    val position = viewModel.changeItemStatus(id)
                }.show()
            }
        }, viewModel.communication)
        recyclerView.adapter = adapter
        viewModel.observeList(this, {
            adapter.update() })
        viewModel.getItemList()
    }

    protected abstract fun getViewModel(app: JokeApp):BaseViewModel<T>
    protected abstract fun getCommunication(app: JokeApp): CommonCommunication<T>

    protected abstract fun actionButtonText(): Int

    protected abstract fun checkBoxText(): Int


}