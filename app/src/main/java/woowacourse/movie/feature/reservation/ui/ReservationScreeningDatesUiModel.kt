package woowacourse.movie.feature.reservation.ui

import woowacourse.movie.model.time.ScreeningDate
import java.time.format.DateTimeFormatter

class ReservationScreeningDatesUiModel(val messages: List<String>) {
    companion object {
        fun of(screeningDates: List<ScreeningDate>): ReservationScreeningDatesUiModel {
            return ReservationScreeningDatesUiModel(screeningDates.map { it.message() })
        }

        private fun ScreeningDate.message() = date.format(DateTimeFormatter.ofPattern("yyyy-M-d"))
    }
}
