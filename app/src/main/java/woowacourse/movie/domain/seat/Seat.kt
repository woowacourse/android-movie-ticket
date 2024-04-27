package woowacourse.movie.domain.seat

data class Seat(val row: String, val col: Int) {
    val grade = SeatGrade.getGrade(row)
    val price = grade.price

    companion object {
        fun of(
            row: String,
            col: String,
        ): Seat {
            return Seat(row = row, col = col.toInt())
        }
    }
}
