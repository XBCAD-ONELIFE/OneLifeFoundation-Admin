package com.example.onelifefoundation_admin.authentication

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.auth.User
import com.example.onelifefoundation_admin.repository.AuthRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(

    private val repository: AuthRepository = AuthRepository()

): ViewModel() {

    val currentyUser = repository.currentUser
    val hasUser: Boolean
        get() = repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUserNameChange(userName: String){
        loginUiState = loginUiState.copy(userName = userName)
    }

    fun onPassWordNameChange(passWord: String){
        loginUiState = loginUiState.copy(passWord = passWord)
    }

    fun onUserNameChangeSignUp(userName: String){
        loginUiState = loginUiState.copy(userNameSignUp = userName)
    }

    fun onPassWordChangeSignUp(password: String){
        loginUiState = loginUiState.copy(passWordSignUp = password)
    }
    fun onConfirmPasswordChange(password: String){
        loginUiState = loginUiState.copy(confirmPassWord = password)
    }


    private fun validateLoginForm() =
        loginUiState.userName.isNotBlank() &&
                loginUiState.passWord.isNotBlank()

    private fun validateSignUpForm()=
        loginUiState.userNameSignUp.isNotBlank() &&
                loginUiState.passWordSignUp.isNotBlank() &&
                loginUiState.confirmPassWord.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch {

        try {
            if (!validateSignUpForm()){
                throw IllegalArgumentException("Email and Password cannot be empty")
            }

            loginUiState = loginUiState.copy(isLoading = true)
            if (loginUiState.passWordSignUp !=
                loginUiState.confirmPassWord){
                throw IllegalArgumentException(
                    "Password do not match, Try again")
            }
            loginUiState = loginUiState.copy(signUpError = null)
            repository.createUser(
                loginUiState.userNameSignUp,
                loginUiState.passWordSignUp
            ){isSuccessful ->
                if (isSuccessful){
                    Toast.makeText(context, "Success Login",
                        Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)

                }else
                    Toast.makeText(context, "Failed Login",
                        Toast.LENGTH_SHORT).show()
                loginUiState = loginUiState.copy(isSuccessLogin = false)
            }

        } catch (e: Exception){

            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()

        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }

    }

    fun loginUser(context: Context) = viewModelScope.launch {

        try {
            if (!validateLoginForm()){
                throw IllegalArgumentException("Email and Password cannot be empty")
            }

            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)
            repository.login(
                loginUiState.userName,
                loginUiState.passWord
            ){isSuccessful ->
                if (isSuccessful){
                    Toast.makeText(context, "Success Login",
                        Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }else
                    Toast.makeText(context, "Failed Login",
                        Toast.LENGTH_SHORT).show()
                loginUiState = loginUiState.copy(isSuccessLogin = false)
            }

        } catch (e: Exception){

            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()

        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }

    }

    fun signOut() = repository.signOut()
}

data class LoginUiState(

    val userName: String = "",
    val passWord: String = "",
    val userNameSignUp: String = "",
    val passWordSignUp: String = "",
    val confirmPassWord: String = "",
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null
)