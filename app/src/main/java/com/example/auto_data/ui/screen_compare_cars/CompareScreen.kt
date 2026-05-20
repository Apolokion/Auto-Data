package com.example.auto_data.ui.screen_compare_cars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import com.example.auto_data.ui.theme.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareScreen(viewModel: CompareScreenViewModel = viewModel()) {
    val allBrands by viewModel.allBrands
    val isLoading by viewModel.isLoading
    val error by viewModel.error

    // List State
    var leftBrandExpanded by remember { mutableStateOf(false) }
    var leftModelExpanded by remember { mutableStateOf(false) }
    var leftGenerationExpanded by remember { mutableStateOf(false) }
    var leftSpecsExpanded by remember { mutableStateOf(false) }

    var rightBrandExpanded by remember { mutableStateOf(false) }
    var rightModelExpanded by remember { mutableStateOf(false) }
    var rightGenerationExpanded by remember { mutableStateOf(false) }
    var rightSpecsExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(Dimensions.topAppBarHeight),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 24.dp)
                            .height(Dimensions.topAppBarHeight),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Compare Cars",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(error!!, color = MaterialTheme.colorScheme.error)
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Left Side
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Car 1", style = MaterialTheme.typography.headlineSmall)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Brands - Left Side
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedLeftBrand.value?.brandName
                                ?: "Select Brand",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = { leftBrandExpanded = !leftBrandExpanded }
                                ) {
                                    Icon(
                                        if (leftBrandExpanded)
                                            Icons.Default.KeyboardArrowUp
                                        else
                                            Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        DropdownMenu(
                            expanded = leftBrandExpanded,
                            onDismissRequest = { leftBrandExpanded = false },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            allBrands.forEach { brand ->
                                DropdownMenuItem(
                                    text = { Text(brand.brandName ?: "") },
                                    onClick = {
                                        viewModel.onLeftBrandSelected(brand)
                                        leftBrandExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Models - Left Side
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedLeftModel.value?.modelName
                                ?: "Select Model",
                            onValueChange = {},
                            readOnly = true,
                            enabled = viewModel.selectedLeftBrand.value != null,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if (viewModel.selectedLeftBrand.value != null) {
                                            leftModelExpanded = !leftModelExpanded
                                        }
                                    }
                                ) {
                                    Icon(
                                        if (leftModelExpanded)
                                            Icons.Default.KeyboardArrowUp
                                        else
                                            Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        DropdownMenu(
                            expanded = leftModelExpanded,
                            onDismissRequest = { leftModelExpanded = false },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            viewModel.leftModels.value.forEach { model ->
                                DropdownMenuItem(
                                    text = { Text(model.modelName ?: "") },
                                    onClick = {
                                        viewModel.onLeftModelSelected(model)
                                        leftModelExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Generations - Left Side
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedLeftGeneration.value?.generation
                                ?: "Select Generation",
                            onValueChange = {},
                            readOnly = true,
                            enabled = viewModel.selectedLeftModel.value != null,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if (viewModel.selectedLeftModel.value != null) {
                                            leftGenerationExpanded = !leftGenerationExpanded
                                        }
                                    }
                                ) {
                                    Icon(
                                        if (leftGenerationExpanded)
                                            Icons.Default.KeyboardArrowUp
                                        else
                                            Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        DropdownMenu(
                            expanded = leftGenerationExpanded,
                            onDismissRequest = { leftGenerationExpanded = false },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            viewModel.leftGenerations.value.forEach { generation ->
                                DropdownMenuItem(
                                    text = { Text(generation.generation ?: "") },
                                    onClick = {
                                        viewModel.onLeftGenerationSelected(generation)
                                        leftGenerationExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Specs - Left Side
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedLeftSpecs.value?.genSpecs
                                ?: "Select Specs",
                            onValueChange = {},
                            readOnly = true,
                            enabled = viewModel.selectedLeftGeneration.value != null,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if (viewModel.selectedLeftGeneration.value != null) {
                                            leftSpecsExpanded = !leftSpecsExpanded
                                        }
                                    }
                                ) {
                                    Icon(
                                        if (leftSpecsExpanded)
                                            Icons.Default.KeyboardArrowUp
                                        else
                                            Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        DropdownMenu(
                            expanded = leftSpecsExpanded,
                            onDismissRequest = { leftSpecsExpanded = false },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            viewModel.leftSpecs.value.forEach { specs ->
                                DropdownMenuItem(
                                    text = { Text(specs.genSpecs ?: "") },
                                    onClick = {
                                        viewModel.onLeftSpecsSelected(specs)
                                        leftSpecsExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                VerticalDivider(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Right Side
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Car 2", style = MaterialTheme.typography.headlineSmall)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Brands - Right Side
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedRightBrand.value?.brandName
                                ?: "Select Brand",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = { rightBrandExpanded = !rightBrandExpanded }
                                ) {
                                    Icon(
                                        if (rightBrandExpanded)
                                            Icons.Default.KeyboardArrowUp
                                        else
                                            Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        DropdownMenu(
                            expanded = rightBrandExpanded,
                            onDismissRequest = { rightBrandExpanded = false },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            allBrands.forEach { brand ->
                                DropdownMenuItem(
                                    text = { Text(brand.brandName ?: "") },
                                    onClick = {
                                        viewModel.onRightBrandSelected(brand)
                                        rightBrandExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Models - Right Side
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedRightModel.value?.modelName
                                ?: "Select Model",
                            onValueChange = {},
                            readOnly = true,
                            enabled = viewModel.selectedRightBrand.value != null,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if (viewModel.selectedRightBrand.value != null) {
                                            rightModelExpanded = !rightModelExpanded
                                        }
                                    }
                                ) {
                                    Icon(
                                        if (rightModelExpanded)
                                            Icons.Default.KeyboardArrowUp
                                        else
                                            Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        DropdownMenu(
                            expanded = rightModelExpanded,
                            onDismissRequest = { rightModelExpanded = false },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            viewModel.rightModels.value.forEach { model ->
                                DropdownMenuItem(
                                    text = { Text(model.modelName ?: "") },
                                    onClick = {
                                        viewModel.onRightModelSelected(model)
                                        rightModelExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Generations - Right Side
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedRightGeneration.value?.generation
                                ?: "Select Generation",
                            onValueChange = {},
                            readOnly = true,
                            enabled = viewModel.selectedRightModel.value != null,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if (viewModel.selectedRightModel.value != null) {
                                            rightGenerationExpanded = !rightGenerationExpanded
                                        }
                                    }
                                ) {
                                    Icon(
                                        if (rightGenerationExpanded)
                                            Icons.Default.KeyboardArrowUp
                                        else
                                            Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        DropdownMenu(
                            expanded = rightGenerationExpanded,
                            onDismissRequest = { rightGenerationExpanded = false },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            viewModel.rightGenerations.value.forEach { generation ->
                                DropdownMenuItem(
                                    text = { Text(generation.generation ?: "") },
                                    onClick = {
                                        viewModel.onRightGenerationSelected(generation)
                                        rightGenerationExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Specs - Right Side
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = viewModel.selectedRightSpecs.value?.genSpecs
                                ?: "Select Specs",
                            onValueChange = {},
                            readOnly = true,
                            enabled = viewModel.selectedRightGeneration.value != null,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if (viewModel.selectedRightGeneration.value != null) {
                                            rightSpecsExpanded = !rightSpecsExpanded
                                        }
                                    }
                                ) {
                                    Icon(
                                        if (rightSpecsExpanded)
                                            Icons.Default.KeyboardArrowUp
                                        else
                                            Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        DropdownMenu(
                            expanded = rightSpecsExpanded,
                            onDismissRequest = { rightSpecsExpanded = false },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            viewModel.rightSpecs.value.forEach { specs ->
                                DropdownMenuItem(
                                    text = { Text(specs.genSpecs ?: "") },
                                    onClick = {
                                        viewModel.onRightSpecsSelected(specs)
                                        rightSpecsExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

