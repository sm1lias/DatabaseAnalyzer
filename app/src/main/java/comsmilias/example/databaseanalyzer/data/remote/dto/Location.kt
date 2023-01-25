package comsmilias.example.databaseanalyzer.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val area: String = "",
    val latitude: String = "",
    val longitude: String = ""
): Parcelable