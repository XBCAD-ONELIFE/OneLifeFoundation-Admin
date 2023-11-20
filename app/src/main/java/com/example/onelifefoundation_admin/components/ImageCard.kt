package com.example.onelifefoundation_admin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.example.onelifefoundation_admin.models.Project




@OptIn(ExperimentalPagerApi::class)
@ExperimentalCoilApi
@Composable
fun Carousel(
    modifier: Modifier = Modifier,
    images: List<String>,
    onClick: () -> Unit,
) {
    // Create a pager state with the initial page set to 0
    val pagerState = rememberPagerState(initialPage = 0)
    // Create a horizontal pager with the given page count and state
    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = modifier
    ) { page ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clickable { onClick.invoke() },
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            AsyncImage(
                model = images[page],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .size(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@ExperimentalCoilApi
@Composable
fun CarouselWithText(
    modifier: Modifier = Modifier,
    images: List<String>,
    onClick: () -> Unit,
    buttonText: String,
    text: String
) {
    // Create a pager state with the initial page set to 0
    val pagerState = rememberPagerState(initialPage = 0)
    // Create a horizontal pager with the given page count and state
    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = modifier
    ) { page ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            AsyncImage(
                model =images[page],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .size(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(text = text, fontSize = 14.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Button(onClick = { onClick.invoke() },
                    modifier= Modifier
                        .align(Alignment.End)
                        .padding(8.dp)) {
                    Text(text = buttonText)
                }
            }

        }
    }
}



@Composable
fun ImageCard(
    image: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    buttonText: String,
    text: String
){
    Column{
        AsyncImage(
            model=image,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .fillMaxWidth(1f),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun ProjectViewList(
    projects: List<Project>,
    onEditClick: (Project) -> Unit,
    onDeleteClick: (Project) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(12.dp)
    ) {
        items(projects.size) { project ->
            ProjectView(
                project = projects[project],
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}

@Composable
fun ProjectView(
    project: Project,
    onEditClick: (Project) -> Unit,
    onDeleteClick: (Project) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var editedProjectName by remember { mutableStateOf(project.projectName) }
    var editedProjectLeader by remember { mutableStateOf(project.projectLeader) }
    var editedProjectLocation by remember { mutableStateOf(project.projectLocation) }
    var editedProjectDescription by remember { mutableStateOf(project.projectDescription) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        val currentProject = if (isEditing) {
            Project(
                editedProjectName,
                editedProjectLeader,
                editedProjectLocation,
                editedProjectDescription,
                emptyList()
            )
        } else {
            project
        }

        AsyncImage(
            model = currentProject.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(1f)
                .size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(16.dp))

        if (isEditing) {
            // Render editable fields when editing
            EditableFields(
                editedProjectName,
                editedProjectLeader,
                editedProjectLocation,
                editedProjectDescription,
                onNameChange = { editedProjectName = it },
                onLeaderChange = { editedProjectLeader = it },
                onLocationChange = { editedProjectLocation = it },
                onDescriptionChange = { editedProjectDescription = it }
            )
        } else {
            // Render project details when not editing
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = currentProject.projectName, fontSize = 22.sp)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = currentProject.projectLeader, fontSize = 18.sp)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = currentProject.projectLocation, fontSize = 18.sp)
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = currentProject.projectDescription,
                    fontSize = 16.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isEditing) {
                // Save changes button when editing
                Button(
                    onClick = {
                        // Save changes and toggle editing state
                        onEditClick(currentProject)
                        isEditing = false
                        // Add a log statement
                        println("Save button clicked: $currentProject")
                    },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(text = "Save")
                }

            } else {
                // Edit button when not editing
                Button(
                    onClick = {
                        // Toggle editing state when Edit is clicked
                        isEditing = true
                    },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(text = "Edit")
                }
            }

            Button(
                onClick = { onDeleteClick(currentProject) },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = "Delete")
            }
        }
    }
}

@Composable
fun EditableFields(
    projectName: String,
    projectLeader: String,
    projectLocation: String,
    projectDescription: String,
    onNameChange: (String) -> Unit,
    onLeaderChange: (String) -> Unit,
    onLocationChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(4.dp)) {
        TextField(
            value = projectName,
            onValueChange = onNameChange,
            label = { Text("Project Name") }
        )
        Spacer(modifier = Modifier.size(4.dp))
        TextField(
            value = projectLeader,
            onValueChange = onLeaderChange,
            label = { Text("Project Leader") }
        )
        Spacer(modifier = Modifier.size(4.dp))
        TextField(
            value = projectLocation,
            onValueChange = onLocationChange,
            label = { Text("Project Location") }
        )
        Spacer(modifier = Modifier.size(4.dp))
        TextField(
            value = projectDescription,
            onValueChange = onDescriptionChange,
            label = { Text("Project Description") }
        )
    }
}


@Composable
fun ProjectCard(
    project: Project,

    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navigate to project details screen */ }, // You can navigate to a details screen when clicking the card
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        AsyncImage(
            model = project.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(1f)
                .size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = project.projectName, fontSize = 22.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = project.projectLeader, fontSize = 18.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = project.projectLocation, fontSize = 18.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = project.projectDescription,
                fontSize = 16.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

            }
        }
    }
}


@Composable
fun ImageWithText(image:String, text:String,
                  buttonText:String, onClick : () -> Unit,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        AsyncImage(
            model=image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(1f)
                .size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Text(text = text, fontSize = 14.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Button(onClick = { onClick.invoke() },
                modifier= Modifier
                    .align(Alignment.End)
                    .padding(8.dp)) {
                Text(text = buttonText)
            }
        }
    }
}
