package woowacourse.movie.domain.movies

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieScheduler(
    private val startDate: LocalDate,
    private val endDate: LocalDate,
) {
    fun getBookableDates(now: LocalDate = LocalDate.now()): List<LocalDate> {
        val bookableDates = mutableListOf<LocalDate>()
        var current = startDate

        while (!current.isAfter(endDate)) {
            if (!current.isBefore(now)) {
                bookableDates.add(current)
            }
            current = current.plusDays(DAYS_TO_ADD)
        }

        return bookableDates
    }

    fun getBookableTimes(
        selectedDate: LocalDate,
        now: LocalDateTime = LocalDateTime.now(),
    ): List<LocalTime> {
        val selectedDayType = DayType.from(selectedDate)
        val firstBookableHour =
            if (selectedDayType == DayType.WEEKEND) WEEKEND_START_TIME else WEEKDAY_START_TIME

        val bookableTimes = mutableListOf<LocalTime>()
        var currentTime =
            LocalDateTime.of(selectedDate, LocalTime.of(firstBookableHour, START_MINUTE))

        while (currentTime.toLocalTime() < LocalTime.MAX &&
            currentTime.toLocalDate().isEqual(selectedDate)
        ) {
            if (currentTime.isAfter(now)) {
                bookableTimes.add(currentTime.toLocalTime())
            }

            currentTime = currentTime.plusHours(HOURS_TO_ADD)
        }

        return bookableTimes
    }

    companion object {
        private const val WEEKDAY_START_TIME = 10
        private const val WEEKEND_START_TIME = 9
        private const val DAYS_TO_ADD = 1L
        private const val HOURS_TO_ADD = 2L
        private const val START_MINUTE = 0
    }
}
