package com.ei.android.jokeapp.gender_detection_app.model

import com.ei.android.jokeapp.gender_detection_app.apiService.GenderFailure

interface ResultCallback {
    fun provideSuccess(gender: Gender)
    fun provideError(error: GenderFailure)
}