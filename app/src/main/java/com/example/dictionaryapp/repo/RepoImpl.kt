package com.example.dictionaryapp.repo

import androidx.lifecycle.LiveData
import com.example.dictionaryapp.data.remote.datasource.ApiDataSource
import com.example.dictionaryapp.data.remote.performGetOperation
import com.example.dictionaryapp.ext.Resource
import com.example.dictionaryapp.data.model.WordResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class RepoImpl @Inject constructor(private val apiDataSource: ApiDataSource) : Repo {


    override fun wordRepo(word: String?): LiveData<Resource<WordResponse>> = performGetOperation {
        apiDataSource.searchWord(word)
    }
}