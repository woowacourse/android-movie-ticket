package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Grade
import woowacourse.movie.domain.model.Position
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats

// TODO: 모든 상영관이 같은 좌석을 가진다고 가정
class DummySeats : SeatsRepository {
    override fun findById(screenId: Int): Seats =
        Seats(
            Seat(Position(0, 0), Grade.B),
            Seat(Position(0, 1), Grade.B),
            Seat(Position(0, 2), Grade.B),
            Seat(Position(0, 3), Grade.B),
            Seat(Position(1, 0), Grade.B),
            Seat(Position(1, 1), Grade.B),
            Seat(Position(1, 2), Grade.B),
            Seat(Position(1, 3), Grade.B),
            Seat(Position(2, 0), Grade.S),
            Seat(Position(2, 1), Grade.S),
            Seat(Position(2, 2), Grade.S),
            Seat(Position(2, 3), Grade.S),
            Seat(Position(3, 0), Grade.S),
            Seat(Position(3, 1), Grade.S),
            Seat(Position(3, 2), Grade.S),
            Seat(Position(3, 3), Grade.S),
            Seat(Position(4, 0), Grade.A),
            Seat(Position(4, 1), Grade.A),
            Seat(Position(4, 2), Grade.A),
            Seat(Position(4, 3), Grade.A),
        )
}
