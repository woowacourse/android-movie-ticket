package woowacourse.movie.model.seat

import woowacourse.movie.model.reservation.ReservationAmount

enum class SeatRating(val rows: List<Int>, val amount: ReservationAmount) {
    B(listOf(1, 2), ReservationAmount(10000)),
    S(listOf(3, 4), ReservationAmount(15000)),
    A(listOf(5), ReservationAmount(12000)),
    ;

    companion object {
        private const val INVALID_SEAT_MESSAGE = "좌석에 해당하는 등급이 존재하지 않습니다."

        fun from(seat: Seat): SeatRating {
            return entries.find { it.rows.contains(seat.row) }
                ?: throw IllegalArgumentException(INVALID_SEAT_MESSAGE)
        }
    }
}
