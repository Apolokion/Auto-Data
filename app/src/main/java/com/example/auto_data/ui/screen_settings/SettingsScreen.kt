package com.example.auto_data.ui.screen_settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel = viewModel()) {

    val darkModeEnabled = remember { viewModel.darkModeEnabled }
    val notificationsEnabled = remember { viewModel.notificationsEnabled }
    remember { viewModel.username }
    val selectedLanguage = remember { viewModel.selectedLanguage }
    val availableLanguages = viewModel.availableLanguages

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Dark Mode Setting
        SettingsToggleItem(
            title = "Dark Mode",
            isChecked = darkModeEnabled.value,
            onToggle = { viewModel.toggleDarkMode() }
        )

        // Notifications Setting
        SettingsToggleItem(
            title = "Enable Notifications",
            isChecked = notificationsEnabled.value,
            onToggle = { viewModel.toggleNotifications() }
        )


        // Language Setting
        SettingsDropdownItem(
            title = "Language",
            selectedItem = selectedLanguage.value,
            items = availableLanguages,
            onItemSelected = { viewModel.setLanguage(it) }
        )

        // Edit Profile
        SettingsButtonItem(
            title = "Edit Profile",
            onClick = { /* Navigate to Edit Profile Screen */ }
        )

        // Appearance
        SettingsButtonItem(
            title = "Appearance",
            onClick = { /* Navigate to Appearance Screen */ }
        )

        // Privacy and Security
        SettingsButtonItem(
            title = "Privacy and Security",
            onClick = { /* Navigate to Privacy and Security Screen */ }
        )

        // Help and Support
        SettingsButtonItem(
            title = "Help and Support",
            onClick = { /* Navigate to Help and Support Screen */ }
        )
    }
}

@Composable
fun SettingsToggleItem(title: String, isChecked: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
        Switch(
            checked = isChecked,
            onCheckedChange = { onToggle(it) }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsDropdownItem(
    title: String,
    selectedItem: String,
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {},
                label = { Text(title) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            onItemSelected(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsButtonItem(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
    }
}
