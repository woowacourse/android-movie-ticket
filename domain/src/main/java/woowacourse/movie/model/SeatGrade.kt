package woowacourse.movie.model

enum class SeatGrade(val ticketPrice: Money) {
    GRADE_A(Money(13000)), GRADE_S(Money(15000)), GRADE_B(Money(12000))
    ;

    companion object {
        fun from(seat: Seat) = when (seat.row) {
            SeatRow.A, SeatRow.B -> GRADE_A
            SeatRow.D, SeatRow.C -> GRADE_S
            SeatRow.E -> GRADE_B
        }
    }
}
