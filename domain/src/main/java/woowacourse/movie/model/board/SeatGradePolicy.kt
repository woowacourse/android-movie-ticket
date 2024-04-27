package woowacourse.movie.model.board

fun interface SeatGradePolicy {
    fun grade(position: Position): SeatGrade
}

class DefaultSeatGradePolicy : SeatGradePolicy {
    override fun grade(position: Position): SeatGrade {
        return when (position.x) {
            in 0..1 -> SeatGrade.B
            in 2..3 -> SeatGrade.S
            else -> SeatGrade.A
        }
    }
}