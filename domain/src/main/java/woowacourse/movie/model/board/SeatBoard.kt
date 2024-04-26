package woowacourse.movie.model.board

import woowacourse.movie.model.HeadCount

data class SeatBoard internal constructor(
    private val headCount: HeadCount,
    private val totalSeats: Seats,
) {
    private val selectedSeats: Seats = totalSeats.selectedSeats()
    private val seatMap: Map<Position, Seat> = totalSeats.toSeatMap()

    val isCompletedSelection get() = (selectedSeats.size == headCount.count)

    fun totalSeats(): List<Seat> = totalSeats.toList()

    fun selectedSeatsPrice(): Long = selectedSeats.totalPrice()

    fun countSelectedSeats(): Int = selectedSeats.size

    fun select(position: Position): SeatSelectionResult {
        val seat = seatMap[position] ?: error("position : $position - 영화관 좌석 내의 위치를 선택 해야합니다.")
        return when (seat.state) {
            SeatState.SELECTED -> {
                val newSeat = seat.copy(state = SeatState.EMPTY)
                Success(board = copy(totalSeats = totalSeats.replace(newSeat)), newSeat)
            }

            SeatState.EMPTY -> {
                if (isCompletedSelection) Failure
                else {
                    val newSeat = seat.copy(state = SeatState.SELECTED)
                    Success(board = copy(totalSeats = totalSeats.replace(newSeat)), newSeat)
                }
            }

            SeatState.RESERVED -> Failure
            SeatState.BANNED -> Failure
        }
    }
}


