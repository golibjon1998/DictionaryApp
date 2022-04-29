package com.example.dictionaryapp.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.dictionaryapp.ext.Resource
import kotlinx.coroutines.Dispatchers


fun <T> performGetOperation(
    networkCall: suspend () -> Resource<T>
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        val responseStatus = networkCall.invoke()
        emit(responseStatus)
    }