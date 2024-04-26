package woowacourse.movie.feature.reservation.ui

import woowacourse.movie.model.time.ScreeningTime
import java.time.format.DateTimeFormatter

fun List<ScreeningTime>.toReservationScreeningTimeUiModels(): List<ReservationScreeningTimeUiModel> {
    return map { ReservationScreeningTimeUiModel(it.message()) }
}

private fun ScreeningTime.message() = time.format(DateTimeFormatter.ofPattern("HH:mm"))
