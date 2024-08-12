package com.example.auto_data.ui.account_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AccountScreen(viewModel: AccountScreenViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = viewModel.username.value,
            onValueChange = { viewModel.username.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (viewModel.username.value.isEmpty()) {
                    Text("Username", color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (viewModel.password.value.isEmpty()) {
                    Text("Password", color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.errorMessage.value.isNotEmpty()) {
            Text(
                text = viewModel.errorMessage.value,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Button(
            onClick = { viewModel.onLogin() },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Login")
        }

        Button(
            onClick = { viewModel.onCreateAccount() },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Create account")
        }
    }
}
