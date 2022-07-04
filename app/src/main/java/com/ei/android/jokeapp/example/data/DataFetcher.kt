package com.ei.android.jokeapp.example.data

interface DataFetcher {
    suspend fun getData(): CommonDataModel
}