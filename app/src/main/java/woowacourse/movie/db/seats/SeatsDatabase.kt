package woowacourse.movie.db.seats

import woowacourse.movie.model.seats.Grade.A
import woowacourse.movie.model.seats.Grade.B
import woowacourse.movie.model.seats.Grade.S
import woowacourse.movie.model.seats.Seat

object SeatsDatabase {
    val seats =
        listOf(
            Seat('A', 1, B),
            Seat('A', 2, B),
            Seat('A', 3, B),
            Seat('A', 4, B),
            Seat('B', 1, B),
            Seat('B', 2, B),
            Seat('B', 3, B),
            Seat('B', 4, B),
            Seat('C', 1, S),
            Seat('C', 2, S),
            Seat('C', 3, S),
            Seat('C', 4, S),
            Seat('D', 1, S),
            Seat('D', 2, S),
            Seat('D', 3, S),
            Seat('D', 4, S),
            Seat('E', 1, A),
            Seat('E', 2, A),
            Seat('E', 3, A),
            Seat('E', 4, A),
        )
}
