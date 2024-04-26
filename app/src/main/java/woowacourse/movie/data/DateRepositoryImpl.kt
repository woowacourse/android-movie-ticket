package woowacourse.movie.data

import woowacourse.movie.domain.repository.DateRepository
import java.time.LocalDate
import java.time.LocalDateTime

class DateRepositoryImpl : DateRepository {
    override fun getDatesBetween(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var currentDate = startDate

        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate)
            currentDate = currentDate.plusDays(DAYS_TO_ADD)
        }

        return dates
    }

    override fun getDateTimes(date: LocalDate): List<LocalDateTime> {
        val dateTimeList = mutableListOf<LocalDateTime>()

        val startTime =
            if (date.dayOfWeek.value in WEEKEND_START_DATE..WEEKEND_END_DATE) {
                LocalDateTime.of(date, java.time.LocalTime.of(WEEKEND_START_HOUR, DEFAULT_MINUTE))
            } else {
                LocalDateTime.of(date, java.time.LocalTime.of(WEEKDAY_START_HOUR, DEFAULT_MINUTE))
            }

        val endTime = LocalDateTime.of(date.plusDays(DAYS_TO_ADD), java.time.LocalTime.of(DEFAULT_HOUR, DEFAULT_MINUTE))

        var currentTime = startTime
        while (currentTime.isBefore(endTime)) {
            dateTimeList.add(currentTime)
            currentTime = currentTime.plusHours(SCREENING_TERM)
        }

        return dateTimeList
    }

    companion object {
        private const val DEFAULT_HOUR = 0
        private const val DEFAULT_MINUTE = 0
        private const val DAYS_TO_ADD = 1L
        private const val WEEKEND_START_DATE = 6
        private const val WEEKEND_END_DATE = 7
        private const val WEEKEND_START_HOUR = 9
        private const val WEEKDAY_START_HOUR = 10
        private const val SCREENING_TERM = 2L
    }
}
