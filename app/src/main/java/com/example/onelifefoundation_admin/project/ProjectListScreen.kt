package com.example.onelifefoundation_admin.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.onelifefoundation_admin.components.ProjectViewList
import com.example.onelifefoundation_admin.components.TopAppBard
import com.example.onelifefoundation_admin.models.Project
import com.example.onelifefoundation_admin.repository.ProjectDataSourceRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


@Composable
fun ProjectListScreen(navController: NavController) {

    val projectDataSourceRepository = ProjectDataSourceRepository()
    // State for holding the list of projects
    val projectsState by projectDataSourceRepository.getAllProjects()
        .collectAsState(initial = emptyList())

    // State for handling loading during delete operation
    var isDeleting by remember { mutableStateOf(false) }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val onDeleteClick: (Project) -> Unit = { project ->
        // Set loading state to true during delete operation
        isDeleting = true

        // Define behavior for deleting the project
        scope.launch {
            // Call the deleteProject function from your repository
            projectDataSourceRepository.deleteProject(project.projectName)

            // Update loading state after the delete operation
            isDeleting = false

            // Show a snack bar indicating project deletion
            scaffoldState.snackbarHostState.showSnackbar("Project deleted")
        }
    }

    val onEditClick: (Project) -> Unit = {editedProject ->
        // Set loading state to true during edit operation
        isDeleting = true

        // Define behavior for editing the project
        scope.launch {
            try {
                // Call the editProject function from your repository with edited information
                projectDataSourceRepository.editProject(

                    editedProject.projectName,
                    editedProject.projectLeader,
                    editedProject.projectLocation,
                    editedProject.projectDescription,
                    editedProject.imageUrls
                )

                // Update loading state after the edit operation
                isDeleting = false

                // Show a snackbar indicating project edit success
                scaffoldState.snackbarHostState.showSnackbar("Project edited")
            } catch (e: Exception) {
                // Handle the case where the edit operation fails
                isDeleting = false
                scaffoldState.snackbarHostState.showSnackbar("Failed to edit project: ${e.message}")
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBard("Project View", navController = navController)
        Spacer(modifier = Modifier.size(16.dp))

        when {
            isDeleting -> {
                // Show a loading indicator during delete operation
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            projectsState.isEmpty() -> {
                // Show a loading indicator or empty state when projects are being loaded
                // or no projects are available
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            else -> {
                // Data loaded, display the project list
                ProjectViewList(projectsState, onEditClick, onDeleteClick)
            }
        }
    }
}