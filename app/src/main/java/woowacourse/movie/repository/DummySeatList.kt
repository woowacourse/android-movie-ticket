package woowacourse.movie.repository

import woowacourse.movie.domain.SeatBoard
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatGrade

object DummySeatList: SeatListRepository {
    override val seatBoards =
        listOf(
            SeatBoard(
                1,
                4,
                5,
                seats = listOf(
                    Seat("A", 1, SeatGrade.B),
                    Seat("A", 2, SeatGrade.B),
                    Seat("A", 3, SeatGrade.B),
                    Seat("A", 4, SeatGrade.B),
                    Seat("B", 1, SeatGrade.B),
                    Seat("B", 2, SeatGrade.B),
                    Seat("B", 3, SeatGrade.B),
                    Seat("B", 4, SeatGrade.B),
                    Seat("C", 1, SeatGrade.S),
                    Seat("C", 2, SeatGrade.S),
                    Seat("C", 3, SeatGrade.S),
                    Seat("C", 4, SeatGrade.S),
                    Seat("D", 1, SeatGrade.S),
                    Seat("D", 2, SeatGrade.S),
                    Seat("D", 3, SeatGrade.S),
                    Seat("D", 4, SeatGrade.S),
                    Seat("E", 1, SeatGrade.A),
                    Seat("E", 2, SeatGrade.A),
                    Seat("E", 3, SeatGrade.A),
                    Seat("E", 4, SeatGrade.A),
                ),
            ),
        )
}