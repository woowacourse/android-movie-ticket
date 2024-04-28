package woowacourse.movie.domain.seat

data class Seat(val row: String, val col: Int) {
    val grade = SeatGrade.getGrade(row)
    val price = grade.price

    companion object {
        fun of(
            row: Char,
            col: Char,
        ): Seat {
            return Seat(row = row.toString(), col = col.toString().toInt())
        }
    }
}
