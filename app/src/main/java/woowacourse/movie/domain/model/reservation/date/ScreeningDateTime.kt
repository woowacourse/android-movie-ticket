package woowacourse.movie.domain.model.reservation.date

import woowacourse.movie.domain.model.ScreeningInfo
import java.time.LocalDate

class ScreeningDateTime(val screeningInfo: ScreeningInfo) {
    val screeningDate = ScreeningDate(screeningInfo.startDate, screeningInfo.runningTime)

    fun changeScreeningDate(
        year: Int,
        month: Int,
        day: Int,
    ) {
        val changingDate = LocalDate.of(year, month, day)
        if (screeningInfo.isInScreeningDateRange(changingDate)) {
            screeningDate.changeDate(year, month, day)
        }
    }

    fun changeScreeningTime(
        hour: Int,
        minute: Int,
    ) {
        screeningDate.changeTime(hour, minute)
    }
}
