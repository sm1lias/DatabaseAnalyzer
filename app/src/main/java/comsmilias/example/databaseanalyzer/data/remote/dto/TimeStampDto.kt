package comsmilias.example.databaseanalyzer.data.remote.dto

import comsmilias.example.databaseanalyzer.domain.model.TimeStamp

data class TimeStampDto(
    val data: List<String> = emptyList(),
    val location: Location = Location(),
    val time: String="",
    val title: String="",
    var timeCaptured:String = ""
)

fun TimeStampDto.toTimeStamp(): TimeStamp {
    return TimeStamp(
        data = data.joinToString(","),
        location = location,
        time = time,
        title = title,
        timeCaptured = timeCaptured
    )
}