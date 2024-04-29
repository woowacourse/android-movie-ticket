package woowacourse.movie.domain.model.reservation.date

import java.time.DayOfWeek
import java.time.LocalDate

class ScreeningDate(
    firstDate: LocalDate,
    runningTime: Int,
) {
    var date: LocalDate = firstDate
        private set
    val screeningTime = ScreeningTime(runningTime, this.isWeekend())

    fun isWeekend(): Boolean {
        return when (date.dayOfWeek) {
            DayOfWeek.SUNDAY -> true
            DayOfWeek.SATURDAY -> true
            else -> false
        }
    }

    fun changeDate(
        year: Int,
        month: Int,
        day: Int,
    ) {
        date = LocalDate.of(year, month, day)
        screeningTime.initStartTime(this.isWeekend())
    }

    fun changeTime(
        hour: Int,
        minute: Int,
    ) {
        screeningTime.changeStartTime(hour, minute, this.isWeekend())
    }
}
