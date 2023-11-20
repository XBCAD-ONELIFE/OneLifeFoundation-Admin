package com.example.onelifefoundation_admin.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.example.onelifefoundation_admin.authentication.LoginViewModel
import com.example.onelifefoundation_admin.authentication.SignInScreen
import com.example.onelifefoundation_admin.authentication.SignUpScreen
import com.example.onelifefoundation_admin.notifications.Notifications
import com.example.onelifefoundation_admin.onboarding.WelcomeScreen
import com.example.onelifefoundation_admin.project.HomeScreen
import com.example.onelifefoundation_admin.project.ManageUsersScreen
import com.example.onelifefoundation_admin.project.ManageRequestScreen
import com.example.onelifefoundation_admin.project.AddProjectScreen
import com.example.onelifefoundation_admin.project.ProjectListScreen


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun AppNavigation(navController: NavHostController,
                  loginViewModel: LoginViewModel,

                  ){


    val context = LocalContext.current

    // Create an instance of Notifications
    val notifications = Notifications(context)


    NavHost(navController = navController, startDestination = Screens.welcomeScreen ){
        composable(route=Screens.welcomeScreen){
            WelcomeScreen(navController)
        }
        composable(route=Screens.signUpScreen){
            SignUpScreen(navController, loginViewModel)
        }
        composable(route=Screens.signInScreen){
            SignInScreen(navController, loginViewModel)
        }
        composable(route=Screens.homeScreen){
            HomeScreen(navController, loginViewModel)
        }
        composable(route=Screens.manageUsersScreen){
            ManageUsersScreen(navController)
        }
        composable(route=Screens.manageRequestScreen){
            ManageRequestScreen(navController)
        }

        composable(route=Screens.addProjectScreen){
            AddProjectScreen(navController, notifications)
        }
        composable(route=Screens.projectListScreen){
            ProjectListScreen(navController)
        }

    }
}