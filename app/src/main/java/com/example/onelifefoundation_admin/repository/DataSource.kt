package com.example.onelifefoundation_admin.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.example.onelifefoundation_admin.models.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class DataSource {
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    fun addProject(
        imageUri: Uri,
        projectName: String,
        projectLeader: String,
        projectLocation: String,
        projectDescription: String,
        imageUrls: List<String>
    ) {
        // 1. Upload the image to Firebase Storage
        val imageFileName = "$projectName.jpg" // Use a unique file name
        val imageRef: StorageReference = storageRef.child(imageFileName)
        val uploadTask = imageRef.putFile(imageUri)
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                throw task.exception ?: Exception("Image upload failed")
            }
            imageRef.downloadUrl
        }.addOnSuccessListener { imageUrl ->
            // 2. Save the download URL in Fire store along with other project details
            val projectMap = hashMapOf(
                "projectName" to projectName,
                "projectLeader" to projectLeader,
                "projectLocation" to projectLocation,
                "projectDescription" to projectDescription,
                "imageUrls" to imageUrls,
                "image" to imageUrl.toString() // Save the image URL
            )

            db.collection("addProject").document(projectName).set(projectMap)
                .addOnCompleteListener {
                    // Handle success
                }.addOnFailureListener {
                    // Handle failure
                }
        }
    }

    fun getAllProjects(): Flow<List<Project>> {
        return flow {
            val querySnapshot = db.collection("addProject").get().await()
            val projects = mutableListOf<Project>()

            for (document in querySnapshot.documents) {
                val project = document.toObject(Project::class.java)
                project?.let {
                    projects.add(it)
                }
            }

            emit(projects)
        }
    }

    fun getAllJoinRequest(): Flow<List<JoinRequest>> {
        return flow {
            val querySnapshot = db.collection("JoinRequests").get().await()
            val projects = mutableListOf<JoinRequest>()

            for (document in querySnapshot.documents) {
                val project = document.toObject(JoinRequest::class.java)
                project?.let {
                    projects.add(it)
                }
            }

            emit(projects)
        }
    }

    fun editProject(
        projectName: String,
        projectLeader: String,
        projectLocation: String,
        projectDescription: String,
        imageUrls: List<String>
    ) {
        val projectRef = db.collection("addProject").document(projectName)

        // Update the fields you want to change
        val updates = hashMapOf<String, Any>(
            "projectLeader" to projectLeader,
            "projectLocation" to projectLocation,
            "projectDescription" to projectDescription,
            "imageUrls" to imageUrls
        )

        projectRef.update(updates)
            .addOnCompleteListener {
                // Handle success
            }.addOnFailureListener {
                // Handle failure
            }
    }

    // Function to delete a project
    fun deleteProject(projectName: String) {
        val projectRef = db.collection("addProject").document(projectName)

        projectRef.delete()
            .addOnCompleteListener {
                // Handle success
            }.addOnFailureListener {
                // Handle failure
            }
    }
}
