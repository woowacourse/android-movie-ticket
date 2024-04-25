package woowacourse.movie.moviereservation.uimodel

import java.time.LocalDate
import java.time.LocalTime

data class ScreeningDateTimeUiModel(
    val date: ScreeningDateUiModel,
    val times: List<ScreeningTimeUiModel>,
) {
    constructor(date: LocalDate, times: List<LocalTime>) : this(ScreeningDateUiModel(date), times.map { ScreeningTimeUiModel(it) })
}
