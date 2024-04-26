package woowacourse.movie.feature.reservation.ui

import woowacourse.movie.model.time.ScreeningDate
import java.time.format.DateTimeFormatter

fun List<ScreeningDate>.toReservationScreeningDateUiModels(): List<ReservationScreeningDateUiModel> {
    return map { ReservationScreeningDateUiModel(it.message()) }
}

private fun ScreeningDate.message() = date.format(DateTimeFormatter.ofPattern("yyyy-M-d"))
