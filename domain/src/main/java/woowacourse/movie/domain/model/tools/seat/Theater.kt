package woowacourse.movie.domain.model.tools.seat

import woowacourse.movie.domain.model.rules.SeatGradeRules

class Theater(private val seats: Map<Location, SeatGrade>) {

    fun getSeatGrade(location: Location) = seats[location]

    companion object {
        fun of(rows: List<SeatRow>, columns: List<Int>): Theater {
            val locations = rows.flatMap { seatRow ->
                setLocation(seatRow, columns)
            }
            val seats = locations.associateWith { SeatGradeRules.getSeatGradeByRow(it) }
            return Theater(seats)
        }

        private fun setLocation(seatRow: SeatRow, columns: List<Int>) =
            columns.map { column -> Location(seatRow, column) }
    }
}
