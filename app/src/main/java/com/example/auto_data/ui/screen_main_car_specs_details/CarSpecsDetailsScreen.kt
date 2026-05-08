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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
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

    val lazyListState = rememberLazyListState()

    val isTopAppBarVisible by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex == 0 &&
                    lazyListState.firstVisibleItemScrollOffset < 64
        }
    }

    val topAppBarOffset by animateDpAsState(
        targetValue = if (isTopAppBarVisible) 0.dp else (-64).dp,
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = innerPadding,
                state = lazyListState
            ) {
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
                    style = MaterialTheme.typography.headlineSmall,
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

                specs.brandName?.let { Text(text = createStyledText("• Brand: ", it))}
                specs.model?.let { Text(text = createStyledText("• Model: ", it))}
                specs.generation?.let { Text(text = createStyledText("• Generation: ", it))}
                specs.bodyType?.let { Text(text = createStyledText("• Body type: ", it))}
                specs.modificationEngine?.let { Text(text = createStyledText("• Engine: ", it))}
                specs.startOfProduction?.let { Text(text = createStyledText("• Production start: ", it.toString()))}
                specs.endOfProduction?.let { Text(text = createStyledText("• Production end: ", it.toString()))}
                specs.powertrainArchitecture?.let { Text(text = createStyledText("• Powertrain: ", it))}
                specs.seats?.let { Text(text = createStyledText("• Seats: ", it.toString()))}
                specs.doors?.let { Text(text = createStyledText("• Doors: ", it.toString()))}
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

                specs.maximumSpeed?.let { Text(text = createStyledText("• Max. speed: ", it))}
                specs.acceleration0To100?.let { Text(text = createStyledText("• 0-100 km/h (0 - 62 mph): ", it))}
                specs.fuelConsumptionUrban?.let { Text(text = createStyledText("• Fuel consumption - urban: ", it))}
                specs.fuelConsumptionExtraUrban?.let { Text(text = createStyledText("• Fuel consumption - extra urban: ", it))}
                specs.fuelConsumptionCombined?.let { Text(text = createStyledText("• Fuel consumption - combined: ", it))}
                specs.co2Emissions?.let { Text(text = createStyledText("• CO2 emissions: ", it))}
                specs.fuelType?.let { Text(text = createStyledText("• Fuel type: ", it))}
                specs.emissionStandard?.let { Text(text = createStyledText("• Emission standard: ", it))}
                specs.weightToPowerRatio?.let { Text(text = createStyledText("• Weight/Power: ", it))}
                specs.weightToTorqueRatio?.let { Text(text = createStyledText("• Weight/Torque: ", it))}
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

                specs.power?.let { Text(text = createStyledText("• Power: ", it))}
                specs.torque?.let { Text(text = createStyledText("• Torque: ", it))}
                specs.engineLayout?.let { Text(text = createStyledText("• Layout: ", it))}
                specs.engineModelCode?.let { Text(text = createStyledText("• Engine code: ", it))}
                specs.engineDisplacement?.let { Text(text = createStyledText("• Displacement: ", it))}
                specs.numberOfCylinders?.let { Text(text = createStyledText("• Cylinders: ", it.toString()))}
                specs.engineConfiguration?.let { Text(text = createStyledText("• Configuration: ", it))}
                specs.cylinderBore?.let { Text(text = createStyledText("• Bore: ", it))}
                specs.pistonStroke?.let { Text(text = createStyledText("• Stroke: ", it))}
                specs.compressionRatio?.let { Text(text = createStyledText("• Compression: ", it))}
                specs.valvesPerCylinder?.let { Text(text = createStyledText("• Valves/Cylinder: ", it.toString()))}
                specs.engineAspiration?.let { Text(text = createStyledText("• Aspiration: ", it))}
                specs.engineOilCapacity?.let { Text(text = createStyledText("• Oil capacity: ", it))}
                specs.engineOilSpecification?.let { Text(text = createStyledText("• Oil spec: ", it))}
                specs.coolantCapacity?.let { Text(text = createStyledText("• Coolant: ", it))}
                specs.valvetrain?.let { Text(text = createStyledText("• Valvetrain: ", it))}
                specs.fuelInjectionSystem?.let { Text(text = createStyledText("• Injection: ", it))}

                //Electric and Hybrid Cars
                specs.grossBatteryCapacity?.let { Text(text = createStyledText("• Gross battery capacity: ", it))}
                specs.netBatteryCapacity?.let { Text(text = createStyledText("• Net (usable) battery capacity: ", it))}
                specs.batteryVoltage?.let { Text(text = createStyledText("• Battery voltage: ", it))}
                specs.batteryTechnology?.let { Text(text = createStyledText("• Battery technology: ", it))}
                specs.batteryWeight?.let { Text(text = createStyledText("• Battery weight: ", it ))}
                specs.batteryLocation?.let { Text(text = createStyledText("• Battery location: ", it))}
                specs.allElectricRange?.let { Text(text = createStyledText("• All-electric range: ", it))}
                specs.averageEnergyConsumption?.let { Text(text = createStyledText("• Average energy consumption: ", it))}
                specs.chargingPorts?.let { Text(text = createStyledText("• Charging ports: ", it))}
                specs.electricMotorPower?.let { Text(text = createStyledText("• Electric motor power: ", it))}
                specs.electricMotorTorque?.let { Text(text = createStyledText("• Electric motor torque: ", it))}
                specs.electricMotorLocation?.let { Text(text = createStyledText("• Electric motor location: ", it))}
                specs.electricMotorType?.let { Text(text = createStyledText("• Electric motor type: ", it))}
                specs.systemPower?.let { Text(text = createStyledText("• System power: ", it))}
                specs.systemTorque?.let { Text(text = createStyledText("• System torque: ", it))}
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
                    text = "Space, Volume and Weight",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                specs.kerbWeight?.let { Text(text = createStyledText("• Kerb weight: ", it))}
                specs.maxWeight?.let { Text(text = createStyledText("• Max. weight: ", it))}
                specs.maxLoad?.let { Text(text = createStyledText("• Max. load: ", it))}
                specs.trunkSpaceMin?.let { Text(text = createStyledText("• Min. trunk: ", it))}
                specs.trunkSpaceMax?.let { Text(text = createStyledText("• Max. trunk: ", it))}
                specs.fuelTankCapacity?.let { Text(text = createStyledText("• Fuel tank: ", it))}
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

                specs.length?.let { Text(text = createStyledText("• Length: ", it))}
                specs.width?.let { Text(text = createStyledText("• Width: ", it))}
                specs.widthIncludingMirrors?.let { Text(text = createStyledText("• Width with mirrors: ", it))}
                specs.height?.let { Text(text = createStyledText("• Height: ", it))}
                specs.wheelbase?.let { Text(text = createStyledText("• Wheelbase: ", it))}
                specs.frontTrack?.let { Text(text = createStyledText("• Front track: ", it))}
                specs.rearTrack?.let { Text(text = createStyledText("• Rear track: ", it))}
                specs.minimumTurningCircle?.let { Text(text = createStyledText("• Turning diameter: ", it))}
                specs.frontOverhang?.let { Text(text = createStyledText("• Front overhang: ", it))}
                specs.rearOverhang?.let { Text(text = createStyledText("• Rear overhang: ", it))}
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

                specs.drivetrainArchitecture?.let { Text(text = createStyledText("• Drivetrain: ", it))}
                specs.driveWheel?.let { Text(text = createStyledText("• Drive wheel: ", it))}
                specs.numberOfGears?.let { Text(text = createStyledText("• Gears: ", it))}
                specs.frontSuspension?.let { Text(text = createStyledText("• Front suspension: ", it))}
                specs.rearSuspension?.let { Text(text = createStyledText("• Rear suspension: ", it))}
                specs.assistingSystems?.let { Text(text = createStyledText("• Assisting systems: ", it))}
                specs.steeringType?.let { Text(text = createStyledText("• Steering type: ", it))}
                specs.powerSteering?.let { Text(text = createStyledText("• Power steering: ", it))}
                specs.frontBrakes?.let { Text(text = createStyledText("• Front brakes: ", it))}
                specs.rearBrakes?.let { Text(text = createStyledText("• Rear brakes: ", it))}
                specs.tiresSize?.let { Text(text = createStyledText("• Tires: ", it))}
                specs.wheelRimsSize?.let { Text(text = createStyledText("• Rims: ", it))}
            }
        }
    }
}

@Composable
fun createStyledText(label: String, value: String) = buildAnnotatedString {
    withStyle(
        style = SpanStyle(
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        append(label)
    }
    withStyle(
        style = SpanStyle(
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.tertiary
        )
    ) {
        append(value)
    }
}