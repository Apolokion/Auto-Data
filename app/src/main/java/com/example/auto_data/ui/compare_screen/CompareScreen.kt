package com.example.auto_data.ui.compare_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun CompareScreen(viewModel: CompareScreenViewModel = viewModel()) {
    val carBrands by remember { mutableStateOf(viewModel.carBrands) }
    val carModels by remember { mutableStateOf(viewModel.carModels) }

    var selectedBrand1 by remember { mutableStateOf(carBrands[0]) }
    var selectedModel1 by remember { mutableStateOf(carModels[0]) }
    var selectedBrand2 by remember { mutableStateOf(carBrands[0]) }
    var selectedModel2 by remember { mutableStateOf(carModels[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DropdownMenu(
            label = "Select first manufacturer",
            items = carBrands,
            selectedItem = selectedBrand1,
            onItemSelected = { selectedBrand1 = it }
        )
        DropdownMenu(
            label = "Select car model",
            items = carModels,
            selectedItem = selectedModel1,
            onItemSelected = { selectedModel1 = it }
        )
        DropdownMenu(
            label = "Select second manufacturer",
            items = carBrands,
            selectedItem = selectedBrand2,
            onItemSelected = { selectedBrand2 = it }
        )
        DropdownMenu(
            label = "Select car model",
            items = carModels,
            selectedItem = selectedModel2,
            onItemSelected = { selectedModel2 = it }
        )
        Button(onClick = {
            // comparison logic here
            val comparisonResult = "Comparing $selectedBrand1 $selectedModel1 with $selectedBrand2 $selectedModel2"
            // Display the result
        }) {
            Text("Compare")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedItem) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            label = { Text(label) },
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
                        selectedText = item
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}
