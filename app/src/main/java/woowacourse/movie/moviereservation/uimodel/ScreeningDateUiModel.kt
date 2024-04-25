package woowacourse.movie.moviereservation.uimodel

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ScreeningDateUiModel(val date: String) {
    constructor(date: LocalDate) : this(
        date.format(dateFormatter),
    )

    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
}
