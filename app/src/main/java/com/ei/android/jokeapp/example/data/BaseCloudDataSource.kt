package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.core.Mapper
import com.ei.android.jokeapp.example.domain.NoConnectionException
import com.ei.android.jokeapp.example.domain.ServiceUnavailableException
import retrofit2.Call
import java.lang.Exception
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T: Mapper<JokeDataModel>>: CloudDataSource {
    protected abstract fun getJokeServerModel(): Call<T>
    override suspend fun getJoke(): JokeDataModel {
         try{
            return getJokeServerModel().execute().body()!!.to()

        }catch (e: Exception){
            if(e is UnknownHostException){
                throw NoConnectionException()
            }else{
                throw ServiceUnavailableException()
            }
        }

    }

}