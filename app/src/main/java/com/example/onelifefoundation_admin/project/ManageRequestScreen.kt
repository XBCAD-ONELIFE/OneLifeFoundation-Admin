package com.example.onelifefoundation_admin.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.onelifefoundation_admin.components.RequestViewList
import com.example.onelifefoundation_admin.components.TopAppBard
import com.example.onelifefoundation_admin.repository.ProjectDataSourceRepository

@Composable
fun ManageRequestScreen(navController: NavController) {
    val projectDataSourceRepository = ProjectDataSourceRepository()

    // Get the list of projects from the data source repository
    val joinRequestState by projectDataSourceRepository.getAllJoinRequest()
        .collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBard(title = "Manage Request", navController = navController)
        Spacer(modifier = Modifier.size(16.dp))

        when {
            joinRequestState.isEmpty() -> {
                // Show a loading indicator
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            else -> {
                // Data loaded, display the list
                RequestViewList(requests = joinRequestState)
            }
        }
    }
}
