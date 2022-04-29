package com.example.dictionaryapp.di

import com.example.dictionaryapp.repo.Repo
import com.example.dictionaryapp.repo.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(impl: RepoImpl): Repo
}