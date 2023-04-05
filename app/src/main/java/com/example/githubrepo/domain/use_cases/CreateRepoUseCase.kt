package com.example.githubrepo.domain.use_cases

import com.example.githubrepo.common.UiEvent
import com.example.githubrepo.data.model.RepoPostBody
import com.example.githubrepo.data.repository.DataRepository
import com.example.githubrepo.domain.model.Github
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CreateRepoUseCase @Inject constructor(
    private val repository: DataRepository,
) {

    operator fun invoke(repoName: String, description: String): Flow<UiEvent<Github>> = flow {
        emit(UiEvent.Loading())
        val rawToken = "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"
        val properHeaderToken = "Token ${rawToken}"
        emit(
            UiEvent.Success(
                repository.postRepo(
                    token = properHeaderToken,
                    repoPostBody = RepoPostBody(
                        name = repoName, description = description
                    )
                )
            )
        )
    }.catch {
        it.printStackTrace()
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}