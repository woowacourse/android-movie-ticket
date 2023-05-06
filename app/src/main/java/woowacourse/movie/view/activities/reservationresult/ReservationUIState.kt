package woowacourse.movie.view.activities.reservationresult

import woowacourse.movie.domain.screening.Reservation
import java.time.LocalDateTime

data class ReservationUIState(
    val movieTitle: String,
    val screeningDateTime: LocalDateTime,
    val audienceCount: Int,
    val seatNames: String,
    val reservationFee: Int
) {
    companion object {
        fun from(reservation: Reservation): ReservationUIState {
            fun createSeatName(row: Int, column: Int): String = ('A' - 1 + row).toString() + column

            return ReservationUIState(
                reservation.movie.title,
                reservation.screeningDateTime,
                reservation.seatPoints.size,
                reservation.seatPoints.map { createSeatName(it.row, it.column) }.sorted()
                    .joinToString { it },
                reservation.fee.amount
            )
        }
    }
}
