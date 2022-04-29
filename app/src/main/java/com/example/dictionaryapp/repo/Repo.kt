package com.example.dictionaryapp.repo

import androidx.lifecycle.LiveData
import com.example.dictionaryapp.data.model.WordResponse
import com.example.dictionaryapp.ext.Resource

interface Repo {

    fun wordRepo(word: String?): LiveData<Resource<WordResponse>>

}