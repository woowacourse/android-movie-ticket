package woowacourse.movie.feature.reservation.ui

import woowacourse.movie.domain.screening.DailySchedule
import woowacourse.movie.feature.reservation.ui.DailyScheduleModel.Companion.DATE_FORMAT
import woowacourse.movie.feature.reservation.ui.DailyScheduleModel.Companion.TIME_FORMAT
import java.time.format.DateTimeFormatter
import java.util.Locale

data class DailyScheduleModel(val date: String, val times: List<String>) {
    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd"
        const val TIME_FORMAT = "hh:mm"
    }
}

fun DailySchedule.toUiModel(): DailyScheduleModel {
    val dateFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.getDefault())
    val timeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(TIME_FORMAT, Locale.getDefault())

    val formattedDate = date.format(dateFormatter)
    val formattedTimes = times.map { it.format(timeFormatter) }

    return DailyScheduleModel(formattedDate, formattedTimes)
}