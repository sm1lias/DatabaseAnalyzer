package comsmilias.example.databaseanalyzer.domain.model

import comsmilias.example.databaseanalyzer.data.remote.dto.Location

data class TimeStamp(
    val data: List<String>,
    val location: Location,
    val time: String,
    val title: String,
    var timeCaptured:String
)