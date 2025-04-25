package woowacourse.movie.domain.model.reservation

import woowacourse.movie.domain.model.cinema.seat.Seat
import java.time.LocalDateTime

class ReservationInfo(
    val title: String,
    val reservationDateTime: LocalDateTime,
    val reservationCount: ReservationCount,
) {
    private val _seats: MutableList<Seat> = mutableListOf()
    val seats: List<Seat> get() = _seats.toList()

    fun isFull(): Boolean = reservationCount.value == seats.size

    fun updateSeats(seat: Seat) {
        if (seats.contains(seat)) {
            _seats.remove(seat)
            return
        }

        require(reservationCount.value > seats.count()) { INVALID_SEATS_SIZE_ERROR_MESSAGE.format(reservationCount.value, seats.count()) }
        _seats.add(seat)
    }

    companion object {
        private const val INVALID_SEATS_SIZE_ERROR_MESSAGE = "예매 인원 보다 선택된 좌석 수가 많을 수 없습니다 (총: %d / 현재: %d)"
    }
}
