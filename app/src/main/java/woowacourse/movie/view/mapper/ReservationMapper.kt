package woowacourse.movie.view.mapper

import com.example.domain.Reservation
import woowacourse.movie.view.model.ReservationUiModel

fun Reservation.toUiModel(): ReservationUiModel = ReservationUiModel(
    movieTitle,
    screeningDateTime,
    seats.size,
    seats.map { it.toUi() },
    finalReservationFee.amount
)
