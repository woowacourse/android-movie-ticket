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
            null -> throw IllegalStateException(DAY_OF_WEEK_NULL_ERROR.format(selectedDate.toString()))
        }
    }

    fun getScreeningTime(selectedDate: LocalDate): List<LocalTime> {
        val dayOfWeekStandard: DayOfWeekStandard = getDayOfWeekStandard(selectedDate)
        return when (dayOfWeekStandard) {
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
        private const val DAY_OF_WEEK_NULL_ERROR =
            "LocalDate의 dayOfWeek값이 null 이 반환되었습니다.null 이 반환된 LocalDate값:(%s)"
        private const val SCREENING_PERIOD_INIT_ERROR = "기간설정 단위가 올바르지 않습니다."
        private val WEEKDAY_TIME_TABLE =
            convertStringListToLocalTimeList(
                listOf(
                    "09:00",
                    "11:00",
                    "13:00",
                    "15:00",
                    "17:00",
                    "19:00",
                    "21:00",
                    "23:00"
                )
            )

        private val WEEKEND_TIME_TABLE =
            convertStringListToLocalTimeList(
                listOf(
                    "10:00",
                    "12:00",
                    "14:00",
                    "16:00",
                    "18:00",
                    "20:00",
                    "22:00",
                    "00:00"
                )
            )

        private fun convertStringListToLocalTimeList(times: List<String>): List<LocalTime> =
            times.map { LocalTime.parse(it) }
    }
}
