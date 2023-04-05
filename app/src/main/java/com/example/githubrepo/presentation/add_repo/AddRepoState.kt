package com.example.githubrepo.presentation.add_repo

import com.example.githubrepo.domain.model.Github

data class AddRepoState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: Github? = null
)
