package com.example.onelifefoundation_admin.authentication

import com.example.onelifefoundation_admin.components.FilledButtond
import com.example.onelifefoundation_admin.navigation.Screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.onelifefoundation_admin.components.TopAppBard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, loginViewModel: LoginViewModel? = null) {
    // Retrieve the loginUiState and error information
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.signUpError != null
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth()) {
        // Top AppBar
        TopAppBard(title = "Sign Up", navController = navController)

        // Input fields for email, password, and confirm password
        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = loginUiState?.userNameSignUp ?: "",
                onValueChange = { loginViewModel?.onUserNameChangeSignUp(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person ,
                        contentDescription = null, )
                },
                label = {
                    androidx.compose.material3.Text(text = "Email")
                },
                isError = isError
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = loginUiState?.passWordSignUp ?: "",
                onValueChange = { loginViewModel?.onPassWordChangeSignUp(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock ,
                        contentDescription = null, )
                },
                label = {
                    androidx.compose.material3.Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = isError
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = loginUiState?.confirmPassWord ?: "",
                onValueChange = { loginViewModel?.onConfirmPasswordChange(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock ,
                        contentDescription = null, )
                },
                label = {
                    androidx.compose.material3.Text(text = "Confirm Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = isError
            )

            // Display error message in red if there's an error
            if (isError) {
                androidx.compose.material3.Text(
                    text = loginUiState?.signUpError ?: "Unknown error",
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.size(4.dp))

            // Sign In link and Sign Up button
            Row {
                androidx.compose.material3.Text(text = "Already have an account,")
                ClickableText(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("please sign in")
                    }
                }, onClick = {
                    navController.navigate(Screens.signInScreen)
                })
            }

            FilledButtond(
                onClick = { loginViewModel?.createUser(context) },
                label = "Sign Up"
            )

            // Display a loading indicator while creating the user
            if (loginUiState?.isLoading == true) {
                CircularProgressIndicator()
            }

            // Automatically navigate to the home screen if the user is signed in
            LaunchedEffect(key1 = loginViewModel?.hasUser) {
                if (loginViewModel?.hasUser == true) {
                    navController.navigate(Screens.homeScreen)
                }
            }
        }
    }
}

