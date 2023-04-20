package woowacourse.movie.ui.seat

import woowacourse.movie.model.SeatPositionState

interface Observer {
    fun updateSelectSeats(
        positionState: List<SeatPositionState>
    )
}
