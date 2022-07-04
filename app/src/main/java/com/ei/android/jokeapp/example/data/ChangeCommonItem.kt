package com.ei.android.jokeapp.example.data

import java.lang.IllegalStateException

interface ChangeCommonItem {
    suspend fun change(changeStatus: ChangeStatus): CommonDataModel

    class Empty:ChangeCommonItem{
        override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
            throw IllegalStateException("empty change joke called")
        }
    }
}