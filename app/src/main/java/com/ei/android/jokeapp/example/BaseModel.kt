package com.ei.android.jokeapp.example

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseModel(
    private val service: JokeService,
    private val resourceManager: ResourceManager
): Model {

    private var callback: ResultCallback? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
    override fun getJoke() {
        service.getJoke().enqueue(object : retrofit2.Callback<ServiceDTO>{
            override fun onResponse(call: Call<ServiceDTO>, response: Response<ServiceDTO>) {
                if(response.isSuccessful){
                    callback?.provide(response.body()!!.toJoke())
                }else{
                    callback?.provide(FailedJoke(serviceUnavailable.getMessage()))
                }
            }

            override fun onFailure(call: Call<ServiceDTO>, t: Throwable) {
                if(t is UnknownHostException)
                    callback?.provide(FailedJoke(serviceUnavailable.getMessage()))
                else
                    callback?.provide(FailedJoke(serviceUnavailable.getMessage()))
            }
        })
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}