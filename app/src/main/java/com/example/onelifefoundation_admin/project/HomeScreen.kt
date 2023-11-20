package com.example.onelifefoundation_admin.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.onelifefoundation_admin.authentication.LoginViewModel
import com.example.onelifefoundation_admin.components.FilledButtond
import com.example.onelifefoundation_admin.components.Title
import com.example.onelifefoundation_admin.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, loginViewModel: LoginViewModel? = null) {

    Column {
        TopAppBar(
            navigationIcon = {},
            actions = {
                IconButton(onClick = { loginViewModel?.signOut()
                    navController.navigate(Screens.signInScreen)
                }) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = null
                    )
                }
            },
            title ={

                Text(text = "Home")
            }
        )

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Title(title = "Welcome Admin")
            Spacer(modifier = Modifier.size(16.dp))
            FilledButtond(onClick = { navController.navigate(Screens.projectListScreen) }, label = "View Projects")
            Spacer(modifier = Modifier.size(16.dp))
            FilledButtond(onClick = { navController.navigate(Screens.addProjectScreen) }, label = "Add Projects")
            Spacer(modifier = Modifier.size(16.dp))
            FilledButtond(onClick = { navController.navigate(Screens.manageUsersScreen) }, label = "Manage Users")
            Spacer(modifier = Modifier.size(16.dp))
            FilledButtond(onClick = { navController.navigate(Screens.manageRequestScreen) }, label = "Manage Request")
        }
    }
}



