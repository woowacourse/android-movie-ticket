package woowacourse.movie.domain.service

import woowacourse.movie.domain.model.Seat
import woowacourse.movie.ui.view.seat.SeatState

class SeatService {
    private val seats = mutableSetOf<Seat>()

    fun toggleSeat(
        seat: Seat,
        headCount: Int,
    ): SeatState =
        when {
            seat in seats -> {
                seats.remove(seat)
                SeatState.DESELECTED
            }

            seats.size >= headCount -> SeatState.LIMIT
            else -> {
                seats.add(seat)
                SeatState.SELECTED
            }
        }
}
