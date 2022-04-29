package com.example.dictionaryapp.data.remote.datasource

import com.example.dictionaryapp.data.remote.BaseDataSource
import com.example.dictionaryapp.data.remote.api.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ApiDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseDataSource() {

    suspend fun searchWord(word: String?) = getResult {
        apiService.searchWord(word)
    }
}