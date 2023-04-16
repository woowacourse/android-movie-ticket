package woowacourse.movie.domain.datetime

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.datetime.DayOfWeekStandard.WEEKDAY
import woowacourse.movie.domain.datetime.DayOfWeekStandard.WEEKEND
import java.time.DayOfWeek.FRIDAY
import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.SATURDAY
import java.time.DayOfWeek.SUNDAY
import java.time.DayOfWeek.THURSDAY
import java.time.DayOfWeek.TUESDAY
import java.time.DayOfWeek.WEDNESDAY
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
class ScreeningPeriod(val start: LocalDate, val end: LocalDate) : Parcelable {
    init {
        validatePeriod()
    }

    private fun validatePeriod() {
        require(start < end) { SCREENING_PERIOD_INIT_ERROR }
    }

    private fun getDayOfWeekStandard(selectedDate: LocalDate): DayOfWeekStandard {
        return when (selectedDate.dayOfWeek) {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> WEEKDAY
            SATURDAY, SUNDAY -> WEEKEND
        }
    }

    fun getScreeningTime(selectedDate: LocalDate): List<LocalTime> {
        return when (getDayOfWeekStandard(selectedDate)) {
            WEEKDAY -> WEEKDAY_TIME_TABLE
            WEEKEND -> WEEKEND_TIME_TABLE
        }
    }

    fun getScreeningDates(): List<LocalDate> {
        var tempLocalDate = start
        val screeningDates = mutableListOf<LocalDate>()
        while (tempLocalDate != end) {
            screeningDates.add(tempLocalDate)
            tempLocalDate = tempLocalDate.plusDays(1)
        }
        screeningDates.add(tempLocalDate)
        return screeningDates
    }

    companion object {
        private const val SCREENING_PERIOD_INIT_ERROR = "기간 설정 단위가 올바르지 않습니다."
        private val WEEKDAY_TIME_TABLE = List(8) { index ->
            LocalTime.of(9, 0).plusHours(2 * index.toLong())
        }
        private val WEEKEND_TIME_TABLE = List(7) { index ->
            LocalTime.of(10, 0).plusHours(2 * index.toLong())
        }
    }
}
