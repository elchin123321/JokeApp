package com.ei.android.jokeapp.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException

class ViewModelsFactory(
    private val jokesModule: JokesModule,
    private val quotesModule: QuotesModule
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val module = when{
            modelClass.isAssignableFrom(JokesViewModel::class.java) ->jokesModule
            modelClass.isAssignableFrom(QuotesViewModel::class.java) ->quotesModule
            else->throw IllegalStateException("unknown type of viewModel")
        }
        return module.getViewModel() as T
    }
}