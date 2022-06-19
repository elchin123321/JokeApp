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
                    callback.provide(response.body()!![0])
                }else{
                    callback.fail(ErrorType.SERVICE_UNAVAILABLE)

                }
            }

            override fun onFailure(call: Call<List<JokeServerModel>>, t: Throwable) {
                if(t is UnknownHostException){
                    callback.fail(ErrorType.NO_CONNECTION)
                }else{
                    callback.fail(ErrorType.SERVICE_UNAVAILABLE)
                    Log.d("Error", "fail")
                    System.out.println(t.message)
                }

            }

        })
    }

}