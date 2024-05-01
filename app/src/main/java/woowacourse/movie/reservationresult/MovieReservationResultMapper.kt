package woowacourse.movie.reservationresult

import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.Seat
import woowacourse.movie.reservationresult.uimodel.ReservationResultUiModel
import woowacourse.movie.reservationresult.uimodel.SeatUiModel

fun MovieReservation.toReservationResultUiModel(): ReservationResultUiModel {
    return ReservationResultUiModel(
        movie.title,
        cancelDeadLine,
        screenDateTime,
        headCount.count,
        reserveSeats.seats.toSeatUiModel(),
        totalPrice.price.toInt(),
    )
}

fun List<Seat>.toSeatUiModel(): List<SeatUiModel> = this.map { SeatUiModel(it.row, it.col) }
