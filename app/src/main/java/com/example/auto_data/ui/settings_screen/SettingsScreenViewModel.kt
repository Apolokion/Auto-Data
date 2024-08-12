package com.example.auto_data.ui.settings_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SettingsScreenViewModel : ViewModel() {
    val darkModeEnabled = mutableStateOf(false)
    val notificationsEnabled = mutableStateOf(true)
    val username = mutableStateOf("")
    val selectedLanguage = mutableStateOf("English")

    val availableLanguages = listOf("English", "Spanish", "French", "German")

    fun toggleDarkMode() {
        darkModeEnabled.value = !darkModeEnabled.value
    }

    fun toggleNotifications() {
        notificationsEnabled.value = !notificationsEnabled.value
    }

    fun setUsername(newUsername: String) {
        username.value = newUsername
    }

    fun setLanguage(language: String) {
        selectedLanguage.value = language
    }
}
