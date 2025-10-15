package com.example.auto_data.ui.screen_main_car_specs_details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.auto_data.R
import com.example.auto_data.ui.theme.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarSpecsDetailsScreen(
    specsId: String?,
    navController: NavHostController,
    viewModel: CarSpecsDetailsScreenViewModel = viewModel()
) {
    val generationSpecs by viewModel.generationSpecs
    val generalSpecs by viewModel.generalSpecs
    val performanceSpecs by viewModel.performanceSpecs
    val engineSpecs by viewModel.engineSpecs
    val spaceVolumeSpecs by viewModel.spaceVolumeSpecs
    val dimensionsSpecs by viewModel.dimensionsSpecs
    val drivetrainSpecs by viewModel.drivetrainSpecs

    val isLoading by viewModel.isLoading
    val error by viewModel.error

    val listState = rememberLazyListState()

    val isTopAppBarVisible by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0 &&
                    listState.firstVisibleItemScrollOffset < 58
        }
    }

    val topAppBarOffset by animateDpAsState(
        targetValue = if (isTopAppBarVisible) 0.dp else (-58).dp,
        label = "topAppBarOffset"
    )

    LaunchedEffect(specsId) {
        if (specsId != null) {
            viewModel.getSpecsDetails(specsId)
        }
    }

    Scaffold(
        topBar = {
            CarSpecsDetailsTopAppBar(
                topAppBarOffset = topAppBarOffset,
                navController = navController,
                generationSpecs = generationSpecs
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
        }
        else if (error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = error!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        else if (generationSpecs != null) {
            LazyColumn(state = listState,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // General Information about Specification
                item {
                    generationSpecs?.let { specs ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 60.dp)
                                .padding(horizontal = 4.dp, vertical = 2.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = specs.genSpecs ?: "Specifications",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                                specs.brand?.let { brand ->
                                    Text(
                                        text = "Brand: $brand",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                                specs.generation?.let { generation ->
                                    Text(
                                        text = "Generation: $generation",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                                specs.years?.let { years ->
                                    Text(
                                        text = "Years: $years",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }
                    }
                }
                    // Detailed Information
                    item { GeneralSpecsSection(generalSpecs) }
                    item { PerformanceSpecsSection(performanceSpecs) }
                    item { EngineSpecsSection(engineSpecs) }
                    item { SpaceVolumeSpecsSection(spaceVolumeSpecs) }
                    item { DimensionsSpecsSection(dimensionsSpecs) }
                    item { DrivetrainSpecsSection(drivetrainSpecs) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarSpecsDetailsTopAppBar(
    topAppBarOffset: androidx.compose.ui.unit.Dp,
    navController: NavHostController,
    generationSpecs: com.example.auto_data.network.CarGenerationSpecs?
) {
    TopAppBar(
        modifier = Modifier
            .height(58.dp)
            .offset(y = topAppBarOffset),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = generationSpecs?.let { specs ->
                        "${specs.brand ?: ""} ${specs.generation ?: ""} ${specs.genSpecs ?: "Specifications"}"
                    } ?: "Specifications Details",
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 2
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(Dimensions.icon_size_normal),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    )
}


// General Specifications Section
@Composable
fun GeneralSpecsSection(specs: com.example.auto_data.network.SpecDetailsGeneral?) {
    if (specs != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "General Information",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                specs.bodyType?.let { Text(text = "• Body Type: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.modificationEngine?.let { Text(text = "• Engine: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.startOfProduction?.let { start ->
                    specs.endOfProduction?.let { end ->
                        Text(text = "• Production: $start - $end", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
                specs.powertrainArchitecture?.let { Text(text = "• Powertrain: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.seats?.let { Text(text = "• Seats: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.doors?.let { Text(text = "• Doors: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.brandName?.let { Text(text = "• Brand: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.model?.let { Text(text = "• Model: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.generation?.let { Text(text = "• Generation: $it", color = MaterialTheme.colorScheme.onPrimary) }
            }
        }
    }
}

// Performance Specifications Section
@Composable
fun PerformanceSpecsSection(specs: com.example.auto_data.network.SpecDetailsPerformance?) {
    if (specs != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Performance",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                specs.maximumSpeed?.let { Text(text = "• Maximum Speed: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.acceleration0To100?.let { Text(text = "• 0-100 km/h: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.fuelConsumptionUrban?.let { Text(text = "• Fuel consumption (economy) - urban: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.fuelConsumptionExtraUrban?.let { Text(text = "• Fuel consumption (economy) - extra urban: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.fuelConsumptionCombined?.let { Text(text = "• Fuel consumption (economy) - combined: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.co2Emissions?.let { Text(text = "• CO2 Emissions: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.fuelType?.let { Text(text = "• Fuel Type: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.emissionStandard?.let { Text(text = "• Emission Standard: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.weightToPowerRatio?.let { Text(text = "• Weight/Power: $it",color = MaterialTheme.colorScheme.onPrimary) }
                specs.weightToTorqueRatio?.let { Text(text = "• Weight/Torque: $it", color = MaterialTheme.colorScheme.onPrimary) }
            }
        }
    }
}

// Engine Specifications Section
@Composable
fun EngineSpecsSection(specs: com.example.auto_data.network.SpecDetailsEngine?) {
    if (specs != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Engine",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                specs.power?.let { Text(text = "• Power: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.torque?.let { Text(text = "• Torque: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.engineLayout?.let { Text(text = "• Layout: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.engineModelCode?.let { Text(text = "• Engine Code: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.engineDisplacement?.let { Text(text = "• Displacement: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.numberOfCylinders?.let { Text(text = "• Cylinders: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.engineConfiguration?.let { Text(text = "• Configuration: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.cylinderBore?.let { Text(text = "• Bore: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.pistonStroke?.let { Text(text = "• Stroke: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.compressionRatio?.let { Text(text = "• Compression: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.valvesPerCylinder?.let { Text(text = "• Valves/Cylinder: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.engineAspiration?.let { Text(text = "• Aspiration: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.engineOilCapacity?.let { Text(text = "• Oil Capacity: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.engineOilSpecification?.let { Text(text = "• Oil Spec: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.coolantCapacity?.let { Text(text = "• Coolant: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.valvetrain?.let { Text(text = "• Valvetrain: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.fuelInjectionSystem?.let { Text(text = "• Injection: $it", color = MaterialTheme.colorScheme.onPrimary) }

                //Electric and Hybrid Cars
                specs.grossBatteryCapacity?.let { Text(text = "• Gross battery capacity: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.netBatteryCapacity?.let { Text(text = "• Net (usable) battery capacity: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.batteryVoltage?.let { Text(text = "• Battery voltage: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.batteryTechnology?.let { Text(text = "• Battery technology: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.batteryWeight?.let { Text(text = "• Battery weight: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.batteryLocation?.let { Text(text = "• Battery location: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.allElectricRange?.let { Text(text = "• All-electric range: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.averageEnergyConsumption?.let { Text(text = "• Average energy consumption: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.chargingPorts?.let { Text(text = "• Charging ports: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.electricMotorPower?.let { Text(text = "• Electric motor power: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.electricMotorTorque?.let { Text(text = "• Electric motor torque: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.electricMotorLocation?.let { Text(text = "• Electric motor location: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.electricMotorType?.let { Text(text = "• Electric motor type: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.systemPower?.let { Text(text = "• System power: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.systemTorque?.let { Text(text = "• System torque: $it", color = MaterialTheme.colorScheme.onPrimary) }
            }
        }
    }
}

// Space & Volume Specifications Section
@Composable
fun SpaceVolumeSpecsSection(specs: com.example.auto_data.network.SpecDetailsSpaceVolume?) {
    if (specs != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Space & Volume",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                specs.kerbWeight?.let { Text(text = "• Kerb Weight: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.fuelTankCapacity?.let { Text(text = "• Fuel Tank: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.maxWeight?.let { Text(text = "• Max Weight: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.maxLoad?.let { Text(text = "• Max Load: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.trunkSpaceMin?.let { Text(text = "• Min Trunk: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.trunkSpaceMax?.let { Text(text = "• Max Trunk: $it", color = MaterialTheme.colorScheme.onPrimary) }
            }
        }
    }
}

// Dimensions Specifications Section
@Composable
fun DimensionsSpecsSection(specs: com.example.auto_data.network.SpecDetailsDimensions?) {
    if (specs != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Dimensions",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                specs.length?.let { Text(text = "• Length: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.width?.let { Text(text = "• Width: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.widthIncludingMirrors?.let { Text(text = "• Width (with mirrors): $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.height?.let { Text(text = "• Height: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.wheelbase?.let { Text(text = "• Wheelbase: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.frontTrack?.let { Text(text = "• Front Track: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.rearTrack?.let { Text(text = "• Rear Track: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.minimumTurningCircle?.let { Text(text = "• Turning Circle: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.frontOverhang?.let { Text(text = "• Front Overhang: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.rearOverhang?.let { Text(text = "• Rear Overhang: $it", color = MaterialTheme.colorScheme.onPrimary) }
            }
        }
    }
}

// Drivetrain Specifications Section
@Composable
fun DrivetrainSpecsSection(specs: com.example.auto_data.network.SpecDetailsDrivetrain?) {
    if (specs != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Drivetrain & Suspension",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                specs.drivetrainArchitecture?.let { Text(text = "• Drivetrain: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.driveWheel?.let { Text(text = "• Drive Wheel: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.numberOfGears?.let { Text(text = "• Gears: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.frontSuspension?.let { Text(text = "• Front Suspension: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.rearSuspension?.let { Text(text = "• Rear Suspension: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.assistingSystems?.let { Text(text = "• Assisting Systems: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.steeringType?.let { Text(text = "• Steering Type: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.powerSteering?.let { Text(text = "• Power Steering: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.frontBrakes?.let { Text(text = "• Front Brakes: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.rearBrakes?.let { Text(text = "• Rear Brakes: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.tiresSize?.let { Text(text = "• Tires: $it", color = MaterialTheme.colorScheme.onPrimary) }
                specs.wheelRimsSize?.let { Text(text = "• Wheel Rims: $it", color = MaterialTheme.colorScheme.onPrimary) }
            }
        }
    }
}