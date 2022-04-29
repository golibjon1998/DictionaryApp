package com.example.dictionaryapp.di

import android.content.Context
import com.example.dictionaryapp.data.local.SharedPref
import com.example.dictionaryapp.data.local.SharedPrefImpl
import com.example.dictionaryapp.data.remote.api.ApiService
import com.example.dictionaryapp.data.remote.datasource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext context: Context): SharedPref =
        SharedPrefImpl(context)


    @Singleton
    @Provides
    fun provideRemote(sharedPref: SharedPref, @ApplicationContext context: Context) =
        RemoteDataSource(sharedPref, context)

    @Singleton
    @Provides
    fun provideWebService(remoteDataSource: RemoteDataSource): ApiService =
        remoteDataSource.buildApi(ApiService::class.java)

}