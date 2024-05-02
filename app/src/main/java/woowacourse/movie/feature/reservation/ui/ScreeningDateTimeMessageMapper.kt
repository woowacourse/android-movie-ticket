package woowacourse.movie.feature.reservation.ui

import woowacourse.movie.model.time.ScreeningDate
import woowacourse.movie.model.time.ScreeningTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun List<ScreeningDate>.screeningDateMessage(): List<String> {
    return map { it.date.format(DateTimeFormatter.ofPattern("yyyy-M-d")) }
}

fun List<ScreeningTime>.screeningTimeMessage(): List<String> {
    return map { it.time.format(DateTimeFormatter.ofPattern("HH:mm")) }
}

fun convertLocalDateTime(
    screeningDateValue: String,
    screeningTimeValue: String,
): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm")
    return LocalDateTime.parse("$screeningDateValue $screeningTimeValue", formatter)
}

fun String.toScreeningDate(): ScreeningDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-M-d")
    return ScreeningDate(LocalDate.parse(this, formatter))
}
