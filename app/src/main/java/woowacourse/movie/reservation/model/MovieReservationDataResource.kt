package woowacourse.movie.reservation.model

import woowacourse.movie.common.MovieDataSource
import java.time.LocalDate
import java.time.LocalTime

object MovieReservationDataResource {
    var movieId: Long = 0

    private const val FIRST_SCREENING_HOUR_WEEKENDS = 9
    private const val FIRST_SCREENING_HOUR_WEEKDAYS = 10
    private const val SCREENING_TIME_INTERVAL = 2
    private const val SCREENING_TIMES_COUNT_WEEKENDS =
        (24 - FIRST_SCREENING_HOUR_WEEKENDS) / SCREENING_TIME_INTERVAL + 1
    private const val SCREENING_TIMES_COUNT_WEEKDAYS =
        (24 - FIRST_SCREENING_HOUR_WEEKDAYS) / SCREENING_TIME_INTERVAL + 1

    private val firstScreeningDate
        get() = MovieDataSource.movieList[movieId.toInt()].firstScreeningDate

    val screeningDates
        get() =
            List(firstScreeningDate.lengthOfMonth()) {
                LocalDate.of(2024, firstScreeningDate.monthValue, it + 1)
            }

    val screeningTimesWeekends =
        List(SCREENING_TIMES_COUNT_WEEKENDS) { index ->
            LocalTime.of(FIRST_SCREENING_HOUR_WEEKENDS + index * SCREENING_TIME_INTERVAL, 0, 0)
        }

    val screeningTimesWeekdays =
        List(SCREENING_TIMES_COUNT_WEEKDAYS) { index ->
            if (index != SCREENING_TIMES_COUNT_WEEKENDS - 1) {
                LocalTime.of(FIRST_SCREENING_HOUR_WEEKDAYS + index * SCREENING_TIME_INTERVAL, 0, 0)
            } else {
                LocalTime.of(0, 0, 0)
            }
        }

    var selectedScreeningTime: LocalTime = LocalTime.of(0, 0, 0)
}
