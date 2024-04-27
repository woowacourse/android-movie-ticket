package woowacourse.movie.feature.reservation.ui

import woowacourse.movie.model.time.ScreeningTime
import java.time.format.DateTimeFormatter

class ReservationScreeningTimesUiModel(val messages: List<String>) {
    companion object {
        fun of(screeningTimes: List<ScreeningTime>): ReservationScreeningTimesUiModel {
            return ReservationScreeningTimesUiModel(screeningTimes.map { it.message() })
        }

        private fun ScreeningTime.message() = time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}
