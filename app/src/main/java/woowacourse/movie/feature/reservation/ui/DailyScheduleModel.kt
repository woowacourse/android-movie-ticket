package woowacourse.movie.feature.reservation.ui

import woowacourse.movie.domain.screening.DailySchedule
import woowacourse.movie.feature.util.DATE_FORMAT_BAR
import woowacourse.movie.feature.util.TIME_FORMAT
import java.time.format.DateTimeFormatter
import java.util.Locale

data class DailyScheduleModel(
    val date: String,
    val times: List<String>,
)

fun DailySchedule.toUiModel(): DailyScheduleModel {
    val dateFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_FORMAT_BAR, Locale.getDefault())
    val timeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(TIME_FORMAT, Locale.getDefault())

    val formattedDate = date.format(dateFormatter)
    val formattedTimes = times.map { it.format(timeFormatter) }

    return DailyScheduleModel(formattedDate, formattedTimes)
}
