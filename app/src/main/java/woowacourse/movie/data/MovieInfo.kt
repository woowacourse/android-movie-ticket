package woowacourse.movie.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.ranges.step

@Parcelize
data class MovieInfo(
    val poster: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val runningTime: String,
) : Parcelable {
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)

    fun getDates(): List<String> {
        val parsedStartDate = LocalDate.parse(startDate, dateFormatter)
        val parsedEndDate = LocalDate.parse(endDate, dateFormatter)

        val dates = mutableListOf<String>()
        var current = parsedStartDate

        while (!current.isAfter(parsedEndDate)) {
            dates.add(current.format(dateFormatter))
            current = current.plusDays(1)
        }

        return dates
    }

    fun getTimes(date: String): List<String> {
        val parsedDate = LocalDate.parse(date, dateFormatter)

        val startHour =
            when (parsedDate.dayOfWeek) {
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> WEEKEND_START_HOUR
                else -> WEEKDAY_START_HOUR
            }

        return (startHour..LAST_HOUR step HOUR_STEP).map { hour ->
            String.format("%02d:00", hour)
        }
    }

    companion object {
        private const val DATE_PATTERN = "yyyy.M.d"

        private const val WEEKEND_START_HOUR = 9
        private const val WEEKDAY_START_HOUR = 10
        private const val LAST_HOUR = 24
        private const val HOUR_STEP = 2
    }
}
