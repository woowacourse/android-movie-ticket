package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class AvailableTimes private constructor(
    localTimes: List<LocalTime>,
) {
    val value: List<String> = localTimes.map(LocalTime::toString)

    companion object {
        private val DEFAULT_WEEKDAY_START_TIME = LocalTime.of(10, 0)
        private val DEFAULT_WEEKEND_START_TIME = LocalTime.of(9, 0)
        private const val DEFAULT_HOUR_SPAN = 2L
        private const val DEFAULT_NUMBER_OF_TIMES = 8
        private const val MINIMUM_NUMBER_OF_TIMES = 1
        private const val MAXIMUM_NUMBER_OF_TIMES = 24
        private const val MINIMUM_HOUR_SPAN = 1
        private const val MAXIMUM_HOUR_SPAN = 24
        private const val ERROR_MAXIMUM_NUMBER_OF_TIMES =
            "하루 상영 횟수는 ${MINIMUM_NUMBER_OF_TIMES}에서 ${MAXIMUM_NUMBER_OF_TIMES}회 사이입니다."
        private const val ERROR_MAXIMUM_HOUR_SPAN =
            "하루 상영 간격은 ${MINIMUM_HOUR_SPAN}에서 ${MAXIMUM_HOUR_SPAN}시간 사이입니다."

        fun of(
            bookingDate: LocalDate,
            numberOfTimes: Int = DEFAULT_NUMBER_OF_TIMES,
            hourSpan: Long = DEFAULT_HOUR_SPAN,
        ): AvailableTimes {
            require(numberOfTimes in MINIMUM_NUMBER_OF_TIMES..MAXIMUM_NUMBER_OF_TIMES) { ERROR_MAXIMUM_NUMBER_OF_TIMES }
            require(hourSpan in MINIMUM_HOUR_SPAN..MAXIMUM_HOUR_SPAN) { ERROR_MAXIMUM_HOUR_SPAN }
            return AvailableTimes(generateTimesByBookingDate(bookingDate, hourSpan, numberOfTimes))
        }

        private fun generateTimesByBookingDate(
            bookingDate: LocalDate,
            hourSpan: Long,
            numberOfTimes: Int,
        ): List<LocalTime> =
            when (bookingDate.dayOfWeek) {
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY ->
                    List(numberOfTimes) { index -> DEFAULT_WEEKDAY_START_TIME.plusHours(hourSpan * index) }

                else ->
                    List(numberOfTimes) { index -> DEFAULT_WEEKEND_START_TIME.plusHours(hourSpan * index) }
            }
    }
}
