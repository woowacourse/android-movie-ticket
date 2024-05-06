package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
class ReservationSchedule(
    var screeningDate: LocalDate = LocalDate.now(),
    var screeningTime: LocalTime = LocalTime.now(),
) : Parcelable {
    fun updateScreeningDate(screeningDate: LocalDate) {
        this.screeningDate = screeningDate
    }

    fun updateScreeningTime(screeningTime: LocalTime) {
        this.screeningTime = screeningTime
    }

    fun obtainScreeningTimes(date: LocalDate): List<Int> {
        return when (date.dayOfWeek) {
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> (WEEKEND_TIME_INTERVAL).map { it }
            else -> (WEEKDAY_TIME_INTERVAL).map { it }
        }
    }

    fun obtainScreeningDates(
        firstScreeningDate: LocalDate,
        lastScreeningDate: LocalDate,
    ): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var currentDate = firstScreeningDate

        while (!currentDate.isAfter(lastScreeningDate)) {
            dates.add(currentDate)
            currentDate = currentDate.plusDays(ONE_DAY)
        }

        return dates.toList()
    }

    companion object {
        private const val ONE_DAY = 1L
        private val WEEKEND_TIME_INTERVAL = 9..23 step 2
        private val WEEKDAY_TIME_INTERVAL = 10..24 step 2
    }
}
