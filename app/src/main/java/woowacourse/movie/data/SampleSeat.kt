package woowacourse.movie.data

import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.SeatGrade
import woowacourse.movie.domain.model.Seats

object SampleSeat {
    val seats =
        Seats(
            listOf(
                Seat("A", 1, SeatGrade.B),
                Seat("A", 2, SeatGrade.B),
                Seat("A", 3, SeatGrade.B),
                Seat("A", 4, SeatGrade.B),
                Seat("B", 1, SeatGrade.B),
                Seat("B", 2, SeatGrade.B),
                Seat("B", 3, SeatGrade.B),
                Seat("B", 4, SeatGrade.B),
                Seat("C", 1, SeatGrade.A),
                Seat("C", 2, SeatGrade.A),
                Seat("C", 3, SeatGrade.A),
                Seat("C", 4, SeatGrade.A),
                Seat("D", 1, SeatGrade.A),
                Seat("D", 2, SeatGrade.A),
                Seat("D", 3, SeatGrade.A),
                Seat("D", 4, SeatGrade.A),
                Seat("E", 1, SeatGrade.S),
                Seat("E", 2, SeatGrade.S),
                Seat("E", 3, SeatGrade.S),
                Seat("E", 4, SeatGrade.S),
            ),
        )
}
