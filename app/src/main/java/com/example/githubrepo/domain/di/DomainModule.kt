package com.example.githubrepo.domain.di

import com.example.githubrepo.data.repository.DataRepository
import com.example.githubrepo.domain.use_cases.GetUserRepoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetRepoUseCase(
        repository: DataRepository
    ): GetUserRepoUseCase {
        return GetUserRepoUseCase(repository)
    }

}