package woowacourse.movie.moviereservation.uimodel

import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class ScreeningTimeUiModel(
    val times: String,
) {
    constructor(time: LocalTime) : this(time.format(timeFormatter))

    companion object {
        private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    }
}
