package com.example.githubrepo.presentation.landing

import com.example.githubrepo.domain.model.Github

class LandingState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Github>? = null
)