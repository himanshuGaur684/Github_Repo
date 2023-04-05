package com.example.githubrepo.domain.use_cases

import com.example.githubrepo.common.UiEvent
import com.example.githubrepo.data.repository.DataRepository
import com.example.githubrepo.domain.model.Github
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserRepoUseCase @Inject constructor(
    private val repository: DataRepository
) {
    operator fun invoke(): Flow<UiEvent<List<Github>>> = flow {
        emit(UiEvent.Loading())
        val userId = "himanshuGaur684"
        emit(UiEvent.Success(repository.getRepos(userId)))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}