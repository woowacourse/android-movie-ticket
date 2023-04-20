package woowacourse.movie.domain.seat

class Seat(val position: SeatPosition, val rank: SeatRank) {

    companion object {
        fun makeSeats(): List<Seat> {
            val seats = mutableListOf<Seat>()
            for (i in SeatRow.values()) {
                for (j in 0..3) {
                    when (i) {
                        SeatRow.A, SeatRow.B -> seats.add(Seat(SeatPosition(i, j), SeatRank.B))
                        SeatRow.C, SeatRow.D -> seats.add(Seat(SeatPosition(i, j), SeatRank.S))
                        SeatRow.E -> seats.add(Seat(SeatPosition(i, j), SeatRank.A))
                    }
                }
            }

            return seats
        }
    }
}
