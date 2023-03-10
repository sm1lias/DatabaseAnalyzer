package comsmilias.example.databaseanalyzer.domain.model

import android.os.Parcelable
import comsmilias.example.databaseanalyzer.data.remote.dto.Location
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeStamp(
    val data: List<String>,
    val location: Location,
    val time: String,
    val title: String,
    var timeCaptured:String
) : Parcelable