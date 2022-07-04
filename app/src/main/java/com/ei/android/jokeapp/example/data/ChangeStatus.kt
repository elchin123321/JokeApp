package com.ei.android.jokeapp.example.data

interface ChangeStatus {
    suspend fun addOrRemove(id: Int, model: CommonDataModel): CommonDataModel
}