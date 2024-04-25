package woowacourse.movie.reservation.model

import java.time.LocalDate
import java.time.LocalTime

object DataResource {
    val screeningDates =
        List(31) {
            LocalDate.of(2024, 3, it + 1)
        }

    val screeningTimesWeekends =
        List(8) { index ->
            LocalTime.of(9 + index * 2, 0, 0)
        }

    val screeningTimesWeekDays =
        List(8) { index ->
            if (index != 7) {
                LocalTime.of(10 + index * 2, 0, 0)
            } else {
                LocalTime.of(0, 0, 0)
            }
        }
}
