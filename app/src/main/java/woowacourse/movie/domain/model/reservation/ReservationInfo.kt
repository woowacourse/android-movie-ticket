package woowacourse.movie.domain.model.reservation

import woowacourse.movie.domain.model.cinema.screen.Seat
import java.time.LocalDateTime

class ReservationInfo(
    val title: String,
    val reservationDateTime: LocalDateTime,
    val reservationCount: ReservationCount,
) {
    private val _seats: MutableList<Seat> = mutableListOf()
    val seats: List<Seat> get() = _seats.toList()

    fun updateSeats(seat: Seat) {
        if (_seats.contains(seat)) {
            _seats.remove(seat)
            return
        }

        require(reservationCount.value > _seats.count()) { INVALID_SEATS_SIZE_ERROR_MESSAGE.format(reservationCount.value, _seats.count()) }
        _seats.add(seat)
    }

    fun canPublish(): Boolean = _seats.size == reservationCount.value

    companion object {
        private const val INVALID_SEATS_SIZE_ERROR_MESSAGE = "예매 인원 수보다 많은 좌석을 선택할 수 없습니다. (총 인원: %d / 선택 좌석: %d)"
    }
}
