package com.ei.android.jokeapp.gender_detection_app.model

import android.util.Log
import com.ei.android.jokeapp.gender_detection_app.apiService.GenderDTO
import com.ei.android.jokeapp.gender_detection_app.apiService.GenderService
import com.ei.android.jokeapp.gender_detection_app.apiService.NoConnection
import com.ei.android.jokeapp.gender_detection_app.apiService.ServiceUnavailable
import com.ei.android.jokeapp.gender_detection_app.resorceManager.ResourceManager
import retrofit2.Call
import retrofit2.Response

class BaseModel(
    private val service: GenderService,
    private val resourceManager: ResourceManager
) :Model{
    private var callback: ResultCallback? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun detectGender(name: String) {
        service.getGender(name).enqueue(object : retrofit2.Callback<GenderDTO>{
            override fun onResponse(call: Call<GenderDTO>, response: Response<GenderDTO>) {
                if(response.isSuccessful){
                    callback?.provideSuccess(response.body()!!.getGender())
                }else{
                    callback?.provideError(serviceUnavailable)
                    Log.d("Errro", "yes erro")
                }
            }

            override fun onFailure(call: Call<GenderDTO>, t: Throwable) {
                if(t is UnknownError)
                    callback?.provideError(noConnection)
                else{
                    callback?.provideError(serviceUnavailable)
                    Log.d("Er", "yes erro")
                    }
            }

        })
    }

    override fun clear() {
        callback = null
    }

}