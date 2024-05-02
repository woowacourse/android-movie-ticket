package woowacourse.movie.model.board

import woowacourse.movie.model.HeadCount

data class SeatBoard(
    val headCount: HeadCount,
    private val totalSeats: Seats,
    val boardSize: BoardSize = BoardSize(20, 20),
) {
    val selectedSeats: Seats = totalSeats.selectedSeats()
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
                SeatSelectionResult.Success(
                    board = copy(totalSeats = totalSeats.replace(newSeat)),
                    newSeat,
                )
            }

            SeatState.EMPTY -> {
                if (isCompletedSelection) {
                    SeatSelectionResult.Failure
                } else {
                    val newSeat = seat.copy(state = SeatState.SELECTED)
                    SeatSelectionResult.Success(
                        board = copy(totalSeats = totalSeats.replace(newSeat)),
                        newSeat,
                    )
                }
            }

            SeatState.RESERVED -> SeatSelectionResult.Failure
            SeatState.BANNED -> SeatSelectionResult.Failure
        }
    }

    companion object {
        val STUB =
            buildSeatBoard {
                size(4, 5)
                headCount(HeadCount(2))
                reservedSeatPositions(
                    setOf(
                        Position(1, 1),
                        Position(3, 2),
                        Position(3, 1),
                        Position(4, 0),
                        Position(0, 3),
                    ),
                )
                bannedPositions(
                    setOf(
                        Position(1, 0),
                        Position(2, 0),
                        Position(3, 0),
                        Position(1, 3),
                        Position(2, 3),
                        Position(3, 3),
                    ),
                )
            }
    }
}
