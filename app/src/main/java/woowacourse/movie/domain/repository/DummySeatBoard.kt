package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.SeatBoard
import woowacourse.movie.domain.model.SeatRank

object DummySeatBoard {
    val seatBoards =
        listOf(
            SeatBoard(
                1,
                4,
                5,
                listOf(
                    Seat("A", 0, SeatRank.PURPLE),
                    Seat("A", 1, SeatRank.PURPLE),
                    Seat("A", 2, SeatRank.PURPLE),
                    Seat("A", 3, SeatRank.PURPLE),
                    Seat("B", 0, SeatRank.PURPLE),
                    Seat("B", 1, SeatRank.PURPLE),
                    Seat("B", 2, SeatRank.PURPLE),
                    Seat("B", 3, SeatRank.PURPLE),
                    Seat("C", 0, SeatRank.GREEN),
                    Seat("C", 1, SeatRank.GREEN),
                    Seat("C", 2, SeatRank.GREEN),
                    Seat("C", 3, SeatRank.GREEN),
                    Seat("D", 0, SeatRank.GREEN),
                    Seat("D", 1, SeatRank.GREEN),
                    Seat("D", 2, SeatRank.GREEN),
                    Seat("D", 3, SeatRank.GREEN),
                    Seat("E", 0, SeatRank.BLUE),
                    Seat("E", 1, SeatRank.BLUE),
                    Seat("E", 2, SeatRank.BLUE),
                    Seat("E", 3, SeatRank.BLUE),
                ),
            ),
        )
}
