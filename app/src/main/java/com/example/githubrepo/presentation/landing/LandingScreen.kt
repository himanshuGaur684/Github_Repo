package com.example.githubrepo.presentation.landing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.githubrepo.common.shareLink
import com.example.githubrepo.domain.model.Github
import com.example.githubrepo.presentation.navigation.NavDestination

@Composable
fun LandingScreen(viewModel: LandingViewModel, navController: NavController) {
    val result = viewModel.repo.value
    val context = LocalContext.current

    ComposableLifecycle { source, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.getRepo()
            }
            else -> {

            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = viewModel.userName) },
            actions = {
                IconButton(onClick = {
                    navController.navigate(NavDestination.ADD.route)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            })
    }) { innerPadding ->
        if (result.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if (result.error.isNotBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding), contentAlignment = Alignment.Center
            ) {
                Text(text = result.error)
            }
        }
        result.data?.let { list ->
            if (list.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    AddNewRepo {
                        navController.navigate(NavDestination.ADD.route)
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    items(list) {
                        ListItem(item = it) {
                            context.shareLink(it)
                        }
                    }
                }
            }

        }
    }


}

@Composable
fun ListItem(item: Github, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { onClick.invoke(item.browserUrl) },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = item.title,
            style = MaterialTheme.typography.h6,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            text = item.description, style = MaterialTheme.typography.body1,
            maxLines = 2, overflow = TextOverflow.Ellipsis
        )
        Divider()

    }
}

@Composable
fun AddNewRepo(onClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Track your favourite repo")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { onClick.invoke() }) {
            Text(text = "Add Now")

        }
    }
}

@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


}

