package com.ei.android.jokeapp.example

import com.ei.android.jokeapp.example.data.NoConnection
import com.ei.android.jokeapp.example.data.ServiceUnavailable

class TestModel(resourceManager: ResourceManager) {

    private var count = 0

    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)

     fun getJoke() {

     }




    fun changeJokeStatus() {

    }

    fun chooseDataSource(favorites: Boolean) {
    }


}
