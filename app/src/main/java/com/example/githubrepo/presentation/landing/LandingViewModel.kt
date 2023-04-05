package com.example.githubrepo.presentation.landing

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepo.common.UiEvent
import com.example.githubrepo.domain.use_cases.GetUserRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val getUserRepoUseCase: GetUserRepoUseCase
) :
    ViewModel() {

    private val _repos = mutableStateOf(LandingState())
    val repo: State<LandingState> get() = _repos


    val userName: String
        get() = "himanshuGaur684"

    fun getRepo() = getUserRepoUseCase().onEach {
        when (it) {
            is UiEvent.Loading -> {
                _repos.value = LandingState(isLoading = true)
            }
            is UiEvent.Success -> {
                _repos.value = LandingState(data = it.data)
            }
            is UiEvent.Error -> {
                _repos.value = LandingState(error = it.message.toString())
            }
        }

    }.launchIn(viewModelScope)


}