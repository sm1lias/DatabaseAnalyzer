package comsmilias.example.databaseanalyzer.domain.model

import comsmilias.example.databaseanalyzer.data.remote.dto.Location

data class TimeStamp(
    val data: String,
    val location: Location = Location(),
    val time: String="",
    val title: String="",
    var timeCaptured:String = ""
)