package com.example.githubrepo.presentation.add_repo

import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.githubrepo.presentation.landing.LandingViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddRepoScreen(viewModel: AddRepoViewModel, navController: NavController) {

    val result = viewModel.addRepo.value
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val floatingActionButton = remember {
        mutableStateOf(true)
    }
    val repoName = remember {
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Add Repo") })
    },
        floatingActionButton = {
            if(floatingActionButton.value){
                FloatingActionButton(onClick = {
                    if (!result.isLoading) {
                        if (validate(title = repoName.value, description = description.value)) {
                            viewModel.createRepo(
                                title = repoName.value,
                                description = description.value
                            )
                        } else {
                            Toast.makeText(context, "Please enter valid things", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }
                }) {
                    if (result.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp))
                    } else {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null)
                    }

                }
            }
        }) { innerPadding ->
        Log.d("TAG", "AddRepoScreen: ${innerPadding}")
        Column(
            modifier = Modifier
                .imePadding()
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(text = "Repo Name", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = repoName.value, onValueChange = {
                    repoName.value = it
                })
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Repo Description", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                minLines = 5,
                modifier = Modifier.fillMaxWidth(),
                value = description.value, onValueChange = {
                    description.value = it
                })
        }
    }

    if(result.error.isNotBlank()){
        floatingActionButton.value = true
    }

    result.data?.let {
        scope.launch {
            floatingActionButton.value = false
            delay(4000)
            navController.popBackStack()
        }
    }

}


private fun validate(title: String, description: String): Boolean {
    return when {
        title.isEmpty() -> {
            false
        }
        description.isEmpty() -> {
            false
        }
        else -> {
            true
        }
    }
}