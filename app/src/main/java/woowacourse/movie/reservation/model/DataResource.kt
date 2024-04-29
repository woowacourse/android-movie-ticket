package woowacourse.movie.reservation.model

import woowacourse.movie.common.MovieDataSource
import java.time.LocalDate
import java.time.LocalTime

object DataResource {
    var movieId: Long = 0

    private val firstScreeningDate
        get() = MovieDataSource.movieList[movieId.toInt()].firstScreeningDate

    val screeningDates
        get() =
            List(firstScreeningDate.lengthOfMonth()) {
                LocalDate.of(2024, firstScreeningDate.monthValue, it + 1)
            }

    val screeningTimesWeekends =
        List(8) { index ->
            LocalTime.of(9 + index * 2, 0, 0)
        }

    val screeningTimesWeekdays =
        List(8) { index ->
            if (index != 7) {
                LocalTime.of(10 + index * 2, 0, 0)
            } else {
                LocalTime.of(0, 0, 0)
            }
        }

    var selectedScreeningTime: LocalTime = LocalTime.of(0, 0, 0)
}
