package com.ei.android.jokeapp.example.data

interface DataFetcher<E> {
    suspend fun getData(): CommonDataModel<E>
}