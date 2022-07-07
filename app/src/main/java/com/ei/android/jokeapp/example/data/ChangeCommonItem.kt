package com.ei.android.jokeapp.example.data

import java.lang.IllegalStateException

interface ChangeCommonItem<E> {
    suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E>

    class Empty<E>:ChangeCommonItem<E>{
        override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
            throw IllegalStateException("empty change joke called")
        }
    }
}