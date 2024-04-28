package woowacourse.movie.model

import woowacourse.movie.model.pricing.Tier
import woowacourse.movie.model.seat.Position
import woowacourse.movie.model.seat.Seat

class Theater(rows: Int, cols: Int) {
    private val positions: Set<Position> = (1..rows).flatMap { y ->
        (1..cols).map { x ->
            Position(x, y)
        }
    }.toSet()
    val seats: Map<Position, Seat> = positions.map {
        val seat = when (it.y) {
            3, 4 -> Seat(Tier.S)
            5 -> Seat(Tier.A)
            else -> Seat(Tier.B)
        }
        Pair(it, seat)
    }.toMap()
}
