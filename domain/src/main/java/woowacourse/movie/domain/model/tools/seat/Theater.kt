package woowacourse.movie.domain.model.tools.seat

import woowacourse.movie.domain.model.rules.SeatGradeRules

class Theater(private val seats: Seats) {

    fun findSeat(location: Location) = seats.findSeatByLocation(location)

    companion object {
        fun of(rows: List<SeatRow>, columns: List<Int>): Theater {
            val locations = rows.flatMap { seatRow ->
                setLocation(seatRow, columns)
            }
            val seats = locations.map { Seat(it, SeatGradeRules.getSeatGradeByRow(it)) }
            return Theater(Seats(seats.toSet()))
        }

        private fun setLocation(seatRow: SeatRow, columns: List<Int>) =
            columns.map { column -> Location(seatRow, column) }
    }
}
