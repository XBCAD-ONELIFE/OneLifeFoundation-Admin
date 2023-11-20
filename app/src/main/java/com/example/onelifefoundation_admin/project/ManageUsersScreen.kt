package com.example.onelifefoundation_admin.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.onelifefoundation_admin.components.CarouselWithText
import com.example.onelifefoundation_admin.components.Title
import com.example.onelifefoundation_admin.components.TopAppBard
import com.example.onelifefoundation_admin.components.User
import com.example.onelifefoundation_admin.navigation.Screens

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ManageUsersScreen(navController: NavController){

    val users= listOf(
        User(
            username = "Tanya",
            email= "tanya@gmail.com"
        ),
        User(
            username = "Joe",
            email= "joe@gmail.com"
        ),
        User(
            username = "Tim",
            email= "tim@gmail.com"
        ),
        User(
            username = "Jane",
            email= "jane@gmail.com"
        ),
        User(
            username = "jp",
            email= "jp@gmail.com"
        ),
    )
    Column {
        TopAppBard("Manage Users",navController)
        Spacer(modifier = Modifier.size(16.dp))
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){

        }
    }
}