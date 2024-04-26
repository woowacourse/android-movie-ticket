package woowacourse.movie.moviereservation.uimodel

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class ScreeningDateTimeUiModel(
    val date: String,
    val times: List<String>,
) {
    constructor(date: LocalDate, times: List<LocalTime>) : this(
        date.format(dateFormatter),
        times.map {
            it.format(
                timeFormatter,
            )
        },
    )

    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    }
}
