package com.example.onelifefoundation_admin.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.example.onelifefoundation_admin.components.CheckBoxWithLabel
import com.example.onelifefoundation_admin.components.FilledButtond
import com.example.onelifefoundation_admin.components.TopAppBarWithNoIcon
import com.example.onelifefoundation_admin.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController, loginViewModel: LoginViewModel? = null) {
    // Retrieve the loginUiState and error information
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current

    Column {
        // Top AppBar
        TopAppBarWithNoIcon(title = "Sign In")
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Input fields for email and password
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = loginUiState?.userName ?: "",
                onValueChange = { loginViewModel?.onUserNameChange(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person ,
                        contentDescription = null, )
                },

                label = {
                    Text(text = "Email")
                },
                isError = isError
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = loginUiState?.passWord ?: "",
                onValueChange = { loginViewModel?.onPassWordNameChange(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock ,
                        contentDescription = null, )
                },
                label = {
                    Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = isError
            )

            // Display error message in red if there's an error
            if (isError) {
                Text(
                    text = loginUiState?.loginError ?: "Unknown error",
                    color = Color.Red
                )
            }

            CheckBoxWithLabel("Stay Sign In ?")
            Spacer(modifier = Modifier.size(4.dp))

            // Sign In button
            FilledButtond(
                onClick = { loginViewModel?.loginUser(context) },
                label = "Sign In"
            )

            Spacer(modifier = Modifier.size(4.dp))

            // "Don't have an account" link
            Row {
                Text(text = "Don't have an account,")
                ClickableText(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("please sign up")
                    }
                }, onClick = {
                    navController.navigate(Screens.signUpScreen)
                })
            }

            // Display a loading indicator while signing in
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
