package apk.karmak.moretech

data class mDataItem(
    val address: String,
    val distance: Int,
    val hasRamp: String,
    val kep: Boolean,
    val latitude: Double,
    val longitude: Double,
    val metroStation: String,
    val myBranch: Boolean,
    val network: Any,
    val officeType: String,
    val openHours: List<OpenHour>,
    val openHoursIndividual: List<OpenHoursIndividual>,
    val rko: String,
    val salePointCode: String,
    val salePointFormat: String,
    val salePointName: String,
    val status: String,
    val suoAvailability: String
)