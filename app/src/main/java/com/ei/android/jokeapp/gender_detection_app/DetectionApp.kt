package com.ei.android.jokeapp.gender_detection_app

import android.app.Application
import com.ei.android.jokeapp.gender_detection_app.apiService.GenderService
import com.ei.android.jokeapp.gender_detection_app.model.BaseModel
import com.ei.android.jokeapp.gender_detection_app.resorceManager.BaseResourceManager
import com.ei.android.jokeapp.gender_detection_app.viewModel.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetectionApp: Application() {
    lateinit var viewModel: ViewModel
    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        viewModel = ViewModel(BaseModel(retrofit.create(GenderService::class.java),BaseResourceManager(this)))
    }
}