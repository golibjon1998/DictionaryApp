package com.example.dictionaryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionaryapp.data.model.WordResponse
import com.example.dictionaryapp.ext.Resource
import com.example.dictionaryapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    var wordList = MutableLiveData<Resource<WordResponse>>()

    fun getWordDefinitionList(word: String?) =
        repo.wordRepo(word)


}