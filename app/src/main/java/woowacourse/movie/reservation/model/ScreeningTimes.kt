package woowacourse.movie.reservation.model

import woowacourse.movie.reservation.model.DataResource.screeningTimesWeekdays
import woowacourse.movie.reservation.model.DataResource.screeningTimesWeekends
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class ScreeningTimes(private val screeningDate: LocalDate) {
    val contents: List<LocalTime>
        get() {
            return if (screeningDate.dayOfWeek == DayOfWeek.SATURDAY || screeningDate.dayOfWeek == DayOfWeek.SUNDAY) {
                screeningTimesWeekends
            } else {
                screeningTimesWeekdays
            }
        }
}
