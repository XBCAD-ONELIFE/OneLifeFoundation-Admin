package com.example.onelifefoundation_admin.repository

import android.net.Uri
import com.example.onelifefoundation_admin.models.Project
import kotlinx.coroutines.flow.Flow

class ProjectDataSourceRepository {

    private val dataSource = DataSource()
    // Add a function to edit a project
    fun editProject(
        projectName: String,
        projectLeader: String,
        projectLocation: String,
        projectDescription: String,
        imageUrls: List<String>
    )
    {
        dataSource.editProject(
            projectName,
            projectLeader,
            projectLocation,
            projectDescription,
            imageUrls
        )
    }
    // Add a function to delete a project
    fun deleteProject(projectName: String) {
        dataSource.deleteProject(projectName)

    }
    fun addProject(
        image: Uri, projectName: String, projectLeader: String,
        projectLocation: String, projectDescription: String, imageUrls: List<String>
    ) {
        dataSource.addProject(image, projectName, projectLeader, projectLocation, projectDescription, imageUrls)
    }

    fun getAllProjects(): Flow<List<Project>> {
        return dataSource.getAllProjects()
    }

    fun getAllJoinRequest(): Flow<List<JoinRequest>> {
        return dataSource.getAllJoinRequest()
    }
}
