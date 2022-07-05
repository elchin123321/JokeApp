package com.ei.android.jokeapp.example.data

interface ChangeStatus<E> {
    suspend fun addOrRemove(id: E, model: CommonDataModel<E>): CommonDataModel<E>
}