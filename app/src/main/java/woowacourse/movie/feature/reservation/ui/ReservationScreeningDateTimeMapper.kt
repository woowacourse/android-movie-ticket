package woowacourse.movie.feature.reservation.ui

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun screeningDateTime(
    screeningDateValue: String,
    screeningTimeValue: String,
): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm")
    return LocalDateTime.parse("$screeningDateValue $screeningTimeValue", formatter)
}
