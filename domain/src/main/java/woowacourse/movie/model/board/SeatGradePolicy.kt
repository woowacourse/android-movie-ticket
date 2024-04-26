package woowacourse.movie.model.board

interface SeatGradePolicy {
    fun grade(position: Position): SeatGrade
}

object DefaultSeatGradePolicy : SeatGradePolicy {
    override fun grade(position: Position): SeatGrade {
        return when (position.y) {
            in 0..1 -> SeatGrade.B
            in 2 .. 3 -> SeatGrade.S
            else -> SeatGrade.A
        }
    }
}