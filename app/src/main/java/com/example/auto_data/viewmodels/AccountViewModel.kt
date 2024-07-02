package com.example.auto_data.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {
    val username = mutableStateOf("")
    val password = mutableStateOf("")
    val errorMessage = mutableStateOf("")

    fun onLogin() {
        // Handle login logic
        errorMessage.value = "Connection error"
    }

    fun onCreateAccount() {
        // Handle account creation logic
        errorMessage.value = "Connection error"
    }
}