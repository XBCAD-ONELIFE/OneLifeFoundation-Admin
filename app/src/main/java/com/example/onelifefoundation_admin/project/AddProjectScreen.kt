package com.example.onelifefoundation_admin.project

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context.*
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.onelifefoundation_admin.R
import com.example.onelifefoundation_admin.components.CheckBoxWithLabel
import com.example.onelifefoundation_admin.components.FilledButtonWithICon
import com.example.onelifefoundation_admin.components.FilledButtond
import com.example.onelifefoundation_admin.components.OutlinedTextFieldD
import com.example.onelifefoundation_admin.components.TopAppBard
import com.example.onelifefoundation_admin.notifications.Notifications
import com.example.onelifefoundation_admin.repository.ProjectDataSourceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProjectScreen(
    navController: NavController,
    notificationHandler: Notifications
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val projectDataSourceRepository = ProjectDataSourceRepository()

    var projectName = remember { mutableStateOf("") }
    var projectLeader = remember { mutableStateOf("") }
    var projectLocation = remember { mutableStateOf("") }
    var description = remember { mutableStateOf("") }


    /**
     * Image upload configuration
     */
    var selectImages = remember { mutableStateOf(listOf<Uri>()) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages.value = selectImages.value + it
        }

    fun submitProject() {
        if (projectName.value.isNotBlank()) {
            scope.launch(Dispatchers.IO) {
                // Check if there are selected images
                if (selectImages.value.isNotEmpty()) {
                    val imageUrlsAsStrings = selectImages.value.map { it.toString() }

                    // Assuming the first image is used as the main project image
                    val imageUri = selectImages.value[0]

                    projectDataSourceRepository.addProject(
                        image = imageUri,
                        projectName = projectName.value,
                        projectLeader = projectLeader.value,
                        projectLocation = projectLocation.value,
                        projectDescription = description.value,
                        imageUrls = imageUrlsAsStrings
                    )

                    // Show a message regardless of the result
                    snackbarHostState.showSnackbar("Project submitted")

                    // Clear the input fields
                    projectName.value = ""
                    projectLeader.value = ""
                    projectLocation.value = ""
                    description.value = ""
                    selectImages.value = emptyList()
                } else {
                    Toast.makeText(context, "Please attach an image", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            // Show an error message if the project name is not provided
            Toast.makeText(context, "Please enter a project name", Toast.LENGTH_SHORT).show()
        }
    }





    Scaffold(
        topBar = {
            TopAppBard("Add Project ", navController = navController)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column(modifier = Modifier.padding(paddingValues = it)) {
            Column(modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)) {
                OutlinedTextFieldD(
                    value = projectName.value,
                    onValueChange = {
                        projectName.value = it
                    },
                    label = "Project name"
                )
                OutlinedTextFieldD(
                    value = projectLeader.value,
                    onValueChange = {
                        projectLeader.value = it
                    },
                    label = "Project leader"
                )
                OutlinedTextFieldD(
                    value = projectLocation.value,
                    onValueChange = {
                        projectLocation.value = it
                    },
                    label = "Project location"
                )

                OutlinedTextFieldD(
                    value = description.value,
                    onValueChange = {
                        description.value = it
                    },
                    label = "Description"
                )
                Spacer(Modifier.size(8.dp))
                Row {
                    OutlinedButton(onClick = {}) {
                        Text(text = "Type of Contribution")
                    }
                    Spacer(Modifier.size(2.dp))
                    FilledButtonWithICon(
                        onClick = {
                            galleryLauncher.launch("image/*")
                        },
                        label = "Attach a picture",
                        icon = Icons.Filled.AttachFile,
                    )
                }

                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    items(selectImages.value.size) { index ->
                        val uri = selectImages.value[index]
                        Image(
                            painter = rememberImagePainter(uri),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(16.dp, 8.dp)
                                .size(100.dp)
                                .clickable {

                                }
                        )
                    }
                }

                CheckBoxWithLabel(label = "Money")
                CheckBoxWithLabel(label = "Skills")
                CheckBoxWithLabel(label = "Goods")
                Spacer(modifier = Modifier.size(4.dp))

                // Add Project button
                FilledButtond(
                    onClick = {

                        submitProject()
                        notificationHandler.showNotification(
                            title = "New Project Added",
                            content = "A new project has been added, go check it out!!",
                            navController,
                        )
                    },
                    label = "Add Project" // You can set the button label as per your requirements
                )
            }
        }
    }
}

