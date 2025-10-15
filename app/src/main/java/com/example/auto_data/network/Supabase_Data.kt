package com.example.auto_data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CarBrand(
    val id: Long,
    @SerialName("brand_name")
    val brandName: String? = null,
    val icon: String? = null
)

@Serializable
data class CarModel(
    val id: Long,
    @SerialName("id_brand")
    val idBrand: Long,
    @SerialName("brand_name")
    val brandName: String? = null,
    @SerialName("model_name")
    val modelName: String? = null,
    val years: String? = null,
    @SerialName("url_pictures")
    val urlPictures: String? = null
)

@Serializable
data class CarGeneration(
    val id: Long,
    @SerialName("brand_name")
    val brandName: String? = null,
    @SerialName("id_model")
    val idModel: Long,
    val generation: String? = null,
    val years: String? = null,
    @SerialName("url_pictures")
    val urlPictures: String? = null
)

@Serializable
data class CarGenerationSpecs(
    val id: Long,
    @SerialName("id_model")
    val idModel: Long,
    @SerialName("id_generation")
    val idGeneration: Long,
    val brand: String? = null,
    val generation: String? = null,
    val years: String? = null,
    @SerialName("gen_specs")
    val genSpecs: String? = null
)

@Serializable
data class SpecDetailsGeneral(
    val id: Long,
    @SerialName("id_gen_specs")
    val idGenSpecs: Long,
    @SerialName("brand_name")
    val brandName: String? = null,
    @SerialName("Model")
    val model: String? = null,
    @SerialName("Generation")
    val generation: String? = null,
    @SerialName("Modification (Engine)")
    val modificationEngine: String? = null,
    @SerialName("Start of production")
    val startOfProduction: Int? = null,
    @SerialName("End of production")
    val endOfProduction: Int? = null,
    @SerialName("Powertrain Architecture")
    val powertrainArchitecture: String? = null,
    @SerialName("Body type")
    val bodyType: String? = null,
    @SerialName("Seats")
    val seats: Int? = null,
    @SerialName("Doors")
    val doors: Int? = null
)

@Serializable
data class SpecDetailsPerformance(
    val id: Long,
    @SerialName("id_gen_specs")
    val idGenSpecs: Long,
    @SerialName("Fuel consumption (economy) - urban")
    val fuelConsumptionUrban: String? = null,
    @SerialName("Fuel consumption (economy) - extra urban")
    val fuelConsumptionExtraUrban: String? = null,
    @SerialName("Fuel consumption (economy) - combined")
    val fuelConsumptionCombined: String? = null,
    @SerialName("CO2 emissions")
    val co2Emissions: String? = null,
    @SerialName("Fuel Type")
    val fuelType: String? = null,
    @SerialName("Acceleration 0 - 100 km/h (0 - 62 mph)")
    val acceleration0To100: String? = null,
    @SerialName("Maximum speed")
    val maximumSpeed: String? = null,
    @SerialName("Emission standard")
    val emissionStandard: String? = null,
    @SerialName("Weight-to-power ratio")
    val weightToPowerRatio: String? = null,
    @SerialName("Weight-to-torque ratio")
    val weightToTorqueRatio: String? = null
)

@Serializable
data class SpecDetailsEngine(
    val id: Long,
    @SerialName("id_gen_specs")
    val idGenSpecs: Long,
    @SerialName("Power")
    val power: String? = null,
    @SerialName("Torque")
    val torque: String? = null,
    @SerialName("Engine layout")
    val engineLayout: String? = null,
    @SerialName("Engine Model/Code")
    val engineModelCode: String? = null,
    @SerialName("Engine displacement")
    val engineDisplacement: String? = null,
    @SerialName("Number of cylinders")
    val numberOfCylinders: Int? = null,
    @SerialName("Engine configuration")
    val engineConfiguration: String? = null,
    @SerialName("Cylinder Bore")
    val cylinderBore: String? = null,
    @SerialName("Piston Stroke")
    val pistonStroke: String? = null,
    @SerialName("Compression ratio")
    val compressionRatio: String? = null,
    @SerialName("Number of valves per cylinder")
    val valvesPerCylinder: Int? = null,
    @SerialName("Engine aspiration")
    val engineAspiration: String? = null,
    @SerialName("Engine oil capacity")
    val engineOilCapacity: String? = null,
    @SerialName("Engine oil specification")
    val engineOilSpecification: String? = null,
    @SerialName("Coolant capacity")
    val coolantCapacity: String? = null,
    @SerialName("Valvetrain")
    val valvetrain: String? = null,
    @SerialName("Fuel injection system")
    val fuelInjectionSystem: String? = null,

    //Electric and Hybrid Cars
    @SerialName("Gross battery capacity")
    val grossBatteryCapacity: String? = null,
    @SerialName("Net (usable) battery capacity")
    val netBatteryCapacity: String? = null,
    @SerialName("Battery voltage")
    val batteryVoltage: String? = null,
    @SerialName("Battery technology")
    val batteryTechnology: String? = null,
    @SerialName("Battery weight")
    val batteryWeight: String? = null,
    @SerialName("Battery location")
    val batteryLocation: String? = null,
    @SerialName("All-electric range")
    val allElectricRange: String? = null,
    @SerialName("Average Energy consumption")
    val averageEnergyConsumption: String? = null,
    @SerialName("Charging ports")
    val chargingPorts: String? = null,
    @SerialName("Electric motor power")
    val electricMotorPower: String? = null,
    @SerialName("Electric motor torque")
    val electricMotorTorque: String? = null,
    @SerialName("Electric motor location")
    val electricMotorLocation: String? = null,
    @SerialName("Electric motor type")
    val electricMotorType: String? = null,
    @SerialName("System power")
    val systemPower: String? = null,
    @SerialName("System torque")
    val systemTorque: String? = null,
)

@Serializable
data class SpecDetailsSpaceVolume(
    val id: Long,
    @SerialName("id_gen_specs")
    val idGenSpecs: Long,
    @SerialName("Kerb Weight")
    val kerbWeight: String? = null,
    @SerialName("Fuel tank capacity")
    val fuelTankCapacity: String? = null,
    @SerialName("Max. weight")
    val maxWeight: String? = null,
    @SerialName("Max. load")
    val maxLoad: String? = null,
    @SerialName("Trunk (boot) space - minimum")
    val trunkSpaceMin: String? = null,
    @SerialName("Trunk (boot) space - maximum")
    val trunkSpaceMax: String? = null
)

@Serializable
data class SpecDetailsDimensions(
    val id: Long,
    @SerialName("id_gen_specs")
    val idGenSpecs: Long,
    @SerialName("Length")
    val length: String? = null,
    @SerialName("Width")
    val width: String? = null,
    @SerialName("Width including mirrors")
    val widthIncludingMirrors: String? = null,
    @SerialName("Height")
    val height: String? = null,
    @SerialName("Wheelbase")
    val wheelbase: String? = null,
    @SerialName("Front track")
    val frontTrack: String? = null,
    @SerialName("Rear track")
    val rearTrack: String? = null,
    @SerialName("Minimum turning circle (turning diameter)")
    val minimumTurningCircle: String? = null,
    @SerialName("Front overhang")
    val frontOverhang: String? = null,
    @SerialName("Rear overhang")
    val rearOverhang: String? = null
)

@Serializable
data class SpecDetailsDrivetrain(
    val id: Long,
    @SerialName("id_gen_specs")
    val idGenSpecs: Long,
    @SerialName("Drivetrain Architecture")
    val drivetrainArchitecture: String? = null,
    @SerialName("Drive wheel")
    val driveWheel: String? = null,
    @SerialName("Number of gears and type of gearbox")
    val numberOfGears: String? = null,
    @SerialName("Front suspension")
    val frontSuspension: String? = null,
    @SerialName("Rear suspension")
    val rearSuspension: String? = null,
    @SerialName("Assisting systems")
    val assistingSystems: String? = null,
    @SerialName("Steering type")
    val steeringType: String? = null,
    @SerialName("Power steering")
    val powerSteering: String? = null,
    @SerialName("Front brakes")
    val frontBrakes: String? = null,
    @SerialName("Rear brakes")
    val rearBrakes: String? = null,
    @SerialName("Tires size")
    val tiresSize: String? = null,
    @SerialName("Wheel rims size")
    val wheelRimsSize: String? = null
)