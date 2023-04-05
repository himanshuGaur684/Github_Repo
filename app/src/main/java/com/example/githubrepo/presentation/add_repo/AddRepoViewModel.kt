package com.example.githubrepo.presentation.add_repo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepo.common.UiEvent
import com.example.githubrepo.domain.use_cases.CreateRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddRepoViewModel @Inject constructor(private val createRepoUseCase: CreateRepoUseCase) :
    ViewModel() {

    private val _addRepo = mutableStateOf(AddRepoState())
    val addRepo: State<AddRepoState> get() = _addRepo


    fun createRepo(title: String, description: String) = createRepoUseCase(
        title, description
    ).onEach {
        when (it) {
            is UiEvent.Loading -> {
                _addRepo.value = AddRepoState(isLoading = true)
            }
            is UiEvent.Success -> {
                _addRepo.value = AddRepoState(data = it.data)
            }
            is UiEvent.Error -> {
                _addRepo.value = AddRepoState(error = it.message.toString())
            }
        }
    }.launchIn(viewModelScope)


}