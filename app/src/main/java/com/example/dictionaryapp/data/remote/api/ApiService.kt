package com.example.dictionaryapp.data.remote.api

import com.example.dictionaryapp.data.model.WordResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/v2/entries/en/{word}")
    suspend fun searchWord(
        @Path("word") word: String?
    ): Response<WordResponse>
}
