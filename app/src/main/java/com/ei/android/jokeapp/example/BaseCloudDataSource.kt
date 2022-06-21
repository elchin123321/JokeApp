package com.ei.android.jokeapp.example

import android.util.Log
import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService): CloudDataSource {
    override fun getJoke(callback: JokeCloudCallback) {
        service.getJoke().enqueue(object: retrofit2.Callback<List<JokeServerModel>>{
            override fun onResponse(
                call: Call<List<JokeServerModel>>,
                response: Response<List<JokeServerModel>>
            ) {
                if(response.isSuccessful){
                    callback.provide(response.body()!![0].toJoke())
                }else{
                    callback.fail(ErrorType.SERVICE_UNAVAILABLE)

                }
            }

            override fun onFailure(call: Call<List<JokeServerModel>>, t: Throwable) {
                val errorType = if(t is UnknownHostException)
                    ErrorType.NO_CONNECTION
                else
                    ErrorType.SERVICE_UNAVAILABLE
                callback.fail(errorType)

            }

        })
    }

}