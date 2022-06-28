package com.ei.android.jokeapp.example.data

import com.ei.android.jokeapp.example.domain.NoConnectionException
import com.ei.android.jokeapp.example.domain.ServiceUnavailableException
import java.lang.Exception
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService): CloudDataSource {
    override suspend fun getJoke(): JokeDataModel {
         try{
            return service.getJoke().execute().body()!![0].to()

        }catch (e: Exception){
            if(e is UnknownHostException){
                throw NoConnectionException()
            }else{
                throw ServiceUnavailableException()
            }
        }

    }

}