package com.example.auto_data.data

import com.example.auto_data.R

data class CarModel(val name: String, val icon: Int? = null)

val carModelsMap = mapOf(
    "Acura" to listOf(
        CarModel("CL", R.drawable.model_2nd_acura_cl),
        CarModel("CSX", R.drawable.model_acura_csx),
        CarModel("EL", R.drawable.model_acura_el),
        CarModel("ILX", R.drawable.model_2019_acura_ilx),
        CarModel("Integra", R.drawable.model_2024_acura_integra),
        CarModel("MDX", R.drawable.model_2022_acura_mdx),
        CarModel("NSX", R.drawable.model_2017_acura_nsx),
        CarModel("RDX", R.drawable.model_2019_acura_rdx),
        CarModel("RL", R.drawable.model_2005_acura_rl),
        CarModel("RLX", R.drawable.model_2014_acura_rlx),
        CarModel("RSX", R.drawable.model_acura_rsx),
        CarModel("SLX", R.drawable.model_acura_slx),
        CarModel("TL", R.drawable.model_2008_acura_tl),
        CarModel("TLX", R.drawable.model_2024_acura_tlx),
        CarModel("TSX", R.drawable.model_2010_acura_tsx),
        CarModel("ZDX", R.drawable.model_2024_acura_zdx),
    ),

    "Alfa Romeo" to listOf(
        CarModel("4C"), CarModel("Giulia"), CarModel("Stelvio"), CarModel("Tonale")
    ),
    "Aston Martin" to listOf(
        CarModel("DB11"), CarModel("DBS"), CarModel("Vantage")
    ),
    "Audi" to listOf(
        CarModel("A3"), CarModel("A4"), CarModel("A6")
    ),
    "Bentley" to listOf(
        CarModel("Bentayga"), CarModel("Continental GT"), CarModel("Flying Spur")
    ),
    "BMW" to listOf(
        CarModel("X1"), CarModel("X3"), CarModel("X5")
    ),
    "Bugatti" to listOf(
        CarModel("Chiron"), CarModel("Divo"), CarModel("Veyron")
    ),
    "Cadillac" to listOf(
        CarModel("CT4"), CarModel("CT5"), CarModel("Escalade")
    ),
    "Chevrolet" to listOf(
        CarModel("Blazer"), CarModel("Camaro"), CarModel("Corvette")
    ),
    "Chrysler" to listOf(
        CarModel("300"), CarModel("Pacifica"), CarModel("Voyager")
    ),
    "Citroen" to listOf(
        CarModel("C3"), CarModel("C4"), CarModel("C5")
    ),
    "Cupra" to listOf(
        CarModel("Ateca"), CarModel("Formentor"), CarModel("Leon")
    ),
    "Dodge" to listOf(
        CarModel("Challenger"), CarModel("Charger"), CarModel("Durango")
    ),
    "Ferrari" to listOf(
        CarModel("F8 Tributo"), CarModel("Portofino"), CarModel("Roma")
    ),
    "Fiat" to listOf(
        CarModel("500"), CarModel("500L"), CarModel("500X")
    ),
    "Ford" to listOf(
        CarModel("Bronco"), CarModel("EcoSport"), CarModel("Escape")
    ),
    "Gmc" to listOf(
        CarModel("Acadia"), CarModel("Canyon"), CarModel("Sierra")
    ),
    "Honda" to listOf(
        CarModel("Accord"), CarModel("Civic"), CarModel("CR-V")
    ),
    "Hyundai" to listOf(
        CarModel("Elantra"), CarModel("Kona"), CarModel("Palisade")
    ),
    "Infiniti" to listOf(
        CarModel("Q50"), CarModel("Q60"), CarModel("QX50")
    ),
    "Jaguar" to listOf(
        CarModel("E-Pace"), CarModel("F-Pace"), CarModel("I-Pace")
    ),
    "Jeep" to listOf(
        CarModel("Cherokee"), CarModel("Compass"), CarModel("Grand Cherokee")
    ),
    "Kia" to listOf(
        CarModel("Cadenza"), CarModel("Forte"), CarModel("K5")
    ),
    "Koenigsegg" to listOf(
        CarModel("Gemera"), CarModel("Jesko"), CarModel("Regera")
    ),
    "Lamborghini" to listOf(
        CarModel("Aventador"), CarModel("Huracan"), CarModel("Urus")
    ),
    "Lancia" to listOf(
        CarModel("Delta"), CarModel("Thema"), CarModel("Voyager")
    ),
    "Land Rover" to listOf(
        CarModel("Defender"), CarModel("Discovery"), CarModel("Range Rover")
    ),
    "Lexus" to listOf(
        CarModel("ES"), CarModel("GX"), CarModel("IS")
    ),
    "Lotus" to listOf(
        CarModel("Elise"), CarModel("Evora"), CarModel("Exige")
    ),
    "Maserati" to listOf(
        CarModel("Ghibli"), CarModel("Levante"), CarModel("Quattroporte")
    ),
    "Mazda" to listOf(
        CarModel("CX-3"), CarModel("CX-30"), CarModel("CX-5")
    ),
    "McLaren" to listOf(
        CarModel("570S"), CarModel("720S"), CarModel("GT")
    ),
    "Mercedes-Benz" to listOf(
        CarModel("A-Class"), CarModel("C-Class"), CarModel("E-Class")
    ),
    "Mini" to listOf(
        CarModel("Clubman"), CarModel("Countryman"), CarModel("Hardtop")
    ),
    "Mitsubishi" to listOf(
        CarModel("Eclipse Cross"), CarModel("Outlander"), CarModel("Pajero")
    ),
    "Nissan" to listOf(
        CarModel("Altima"), CarModel("Kicks"), CarModel("Rogue")
    ),
    "Opel" to listOf(
        CarModel("Astra"), CarModel("Corsa"), CarModel("Insignia")
    ),
    "Pagani" to listOf(
        CarModel("Huayra"), CarModel("Huayra Roadster"), CarModel("Zonda")
    ),
    "Peugeot" to listOf(
        CarModel("2008"), CarModel("3008"), CarModel("5008")
    ),
    "Porsche" to listOf(
        CarModel("911"), CarModel("Cayenne"), CarModel("Panamera")
    ),
    "Ram" to listOf(
        CarModel("1500"), CarModel("2500"), CarModel("3500")
    ),
    "Renault" to listOf(
        CarModel("Arkana"), CarModel("Captur"), CarModel("Koleos")
    ),
    "Rolls Royce" to listOf(
        CarModel("Cullinan"), CarModel("Ghost"), CarModel("Phantom")
    ),
    "Seat" to listOf(
        CarModel("Arona"), CarModel("Ibiza"), CarModel("Leon")
    ),
    "Skoda" to listOf(
        CarModel("Enyaq"), CarModel("Kamiq"), CarModel("Karoq")
    ),
    "Smart" to listOf(
        CarModel("EQ ForTwo"), CarModel("EQ ForFour")
    ),
    "Subaru" to listOf(
        CarModel("Ascent"), CarModel("Crosstrek"), CarModel("Forester")
    ),
    "Suzuki" to listOf(
        CarModel("Baleno"), CarModel("Ignis"), CarModel("Swift")
    ),
    "Tesla" to listOf(
        CarModel("Model 3"), CarModel("Model S"), CarModel("Model X")
    ),
    "Toyota" to listOf(
        CarModel("4Runner"), CarModel("Camry"), CarModel("Corolla")
    ),
    "Volkswagen" to listOf(
        CarModel("Arteon"), CarModel("Atlas"), CarModel("Golf")
    ),
    "Volvo" to listOf(
        CarModel("S60"), CarModel("S90"), CarModel("XC40")
    )
)
