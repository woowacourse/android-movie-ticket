package woowacourse.movie.domain.seat

class Seat(val position: SeatPosition, val rank: SeatRank) {

    companion object {
        fun makeSeats(): List<Seat> {
            val position = makeSeatPosition()
            return position.map {
                when (it.row) {
                    SeatRow.A, SeatRow.B -> Seat(it, SeatRank.B)
                    SeatRow.C, SeatRow.D -> Seat(it, SeatRank.S)
                    SeatRow.E -> Seat(it, SeatRank.A)
                }
            }
        }

        private fun makeSeatPosition(): List<SeatPosition> {
            return SeatRow.values().flatMap { seatRow ->
                (0..3).map {
                    SeatPosition(seatRow, it)
                }
            }
        }
    }
}
